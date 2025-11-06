// java
package ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.infra.security;

import ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.domain.user.User;
import ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@AllArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {

//    private final TokenService tokenService;
//    private final UserRepository userRepository;
//
//    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
//        this.tokenService = tokenService;
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = recoverToken(request);
//        String subject = tokenService.validateToken(token);
//
//        if (subject != null) {
//            try {
//                int drt = Integer.parseInt(subject);
//                User user = userRepository.findByDrtUsuario(drt)
//                        .orElseThrow(() -> new RuntimeException("User not found"));
//                var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//                var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } catch (NumberFormatException ignored) {
//                // invalid subject -> ignore authentication
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private String recoverToken(HttpServletRequest request) {
//        String header = request.getHeader("Authorization");
//        if (header == null || header.isBlank()) return null;
//        if (header.startsWith("Bearer ")) return header.substring(7);
//        return header;
//    }

    TokenService TokenService;
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var loginToken = TokenService.validateToken(token);

        if(loginToken != null){
            User user = userRepository.findByDrtUsuario(Integer.parseInt(loginToken)).orElseThrow(() -> new RuntimeException("User Not Found"));
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
