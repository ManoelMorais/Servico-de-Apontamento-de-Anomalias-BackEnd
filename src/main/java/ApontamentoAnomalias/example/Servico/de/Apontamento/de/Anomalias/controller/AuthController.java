package ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.controller;

import ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.domain.user.User;
import ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.dto.RegisterRequestDTO;
import ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.dto.ResponseDTO;
import ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.dto.loginRequestDTO;
import ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.infra.security.TokenService;
import ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody loginRequestDTO body) {
        User user = this.userRepository.findByDrtUsuario(body.drtUsuario()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.senhaUsuario(), user.getSenhaUsuario())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(
                    new ResponseDTO(
                            user.getDrtUsuario(),
                            token,
                            user.getNomeUsuario(),
                            user.getCargoUsuario()
                    ));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional<User> user = this.userRepository.findByDrtUsuario(body.drt());

        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setSenhaUsuario(passwordEncoder.encode(body.password()));
            newUser.setDrtUsuario(body.drt());
            newUser.setNomeUsuario(body.name());
            newUser.setCargoUsuario(body.cargo());
            this.userRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(
                    new ResponseDTO(
                            newUser.getDrtUsuario(),
                            token,
                            newUser.getNomeUsuario(),
                            newUser.getCargoUsuario()
                    ));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/user/{drt}")
    public ResponseEntity<Void> deleteUser(@PathVariable("drt") Integer drt){
        Optional<User> user = this.userRepository.findByDrtUsuario(drt);
        if(user.isPresent()){
            this.userRepository.delete(user.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}

