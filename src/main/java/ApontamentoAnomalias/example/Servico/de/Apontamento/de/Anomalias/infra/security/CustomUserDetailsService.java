package ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.infra.security;

import ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.domain.user.User;
import ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByDrtUsuario(Integer.parseInt(username)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(String.valueOf(user.getDrtUsuario()), user.getSenhaUsuario(), new ArrayList<>());
    }
}
