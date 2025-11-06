package ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.dto;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("""
                        https://servico-de-apontamento-de-anomalias.onrender.com
                        """)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .allowedHeaders("*") // <- permite qualquer cabeçalho
                .allowCredentials(true); // <- permite envio de cookies/autenticação se necessário
    }
}
