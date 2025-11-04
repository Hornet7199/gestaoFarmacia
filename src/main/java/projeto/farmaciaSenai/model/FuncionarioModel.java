package projeto.farmaciaSenai.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "TBFUNCIONARIO")
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FUNCIONARIO")
    private Integer idFuncionario;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private UsuarioModel usuario;

    @Column(name = "NIVEL_ACESSO", length = 20)
    private String nivelAcesso;

    @Column(name = "CARGO", length = 50)
    private String cargo;

    @Column(name = "DATA_ADMISSAO")
    private LocalDate dataAdmissao;

    @Column(name = "SALARIO", precision = 14, scale = 2)
    private BigDecimal salario;

    @Column(name = "BENEFICIOS", length = 255)
    private String beneficios;

    @Column(name = "FLAG_ATIVO")
    private Boolean flagAtivo;
}
