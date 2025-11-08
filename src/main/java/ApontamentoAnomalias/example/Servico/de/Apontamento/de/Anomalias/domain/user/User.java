package ApontamentoAnomalias.example.Servico.de.Apontamento.de.Anomalias.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int drtUsuario;
    private String nomeUsuario;
    private String senhaUsuario;
    private String cargoUsuario;

}
