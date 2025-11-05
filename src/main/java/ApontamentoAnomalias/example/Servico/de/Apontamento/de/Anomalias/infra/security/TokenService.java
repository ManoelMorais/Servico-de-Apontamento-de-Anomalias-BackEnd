// java
package ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.infra.security;

import ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
    private final String secret;

    public TokenService(@Value("${api.security.token.secret}") String secret) {
        if (secret == null || secret.isBlank()) {
            logger.warn("Property api.security.token.secret not set. Using temporary dev-secret. Set env var API_SECURITY_TOKEN_SECRET or add to application.properties.");
            this.secret = "dev-secret"; // local-only fallback
        } else {
            this.secret = secret;
        }
    }

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(String.valueOf(user.getDrtUsuario()))
                    .withExpiresAt(Date.from(generateExpirationInstant()))
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("JWT creation error", e);
        }
    }

    public String validateToken(String token) {
        if (token == null || token.isBlank()) return null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private java.time.Instant generateExpirationInstant() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
