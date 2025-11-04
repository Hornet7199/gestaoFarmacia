package projeto.farmaciaSenai.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "TBUSUARIO")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    private String email;

    @CPF
    @Column(name = "CPF", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @Column(name = "TELEFONE", length = 20)
    private String telefone;
}
