package projeto.farmaciaSenai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBFUNCIONARIO")
public class Funcionario extends Usuario {

    @Column(name = "NIVEL_ACESSO", length = 20)
    private String nivelAcesso;

    @Column(name = "CARGO", length = 50)
    private String cargo;

    @Column(name = "DATA_ADMISSAO")
    private LocalDate dataAdmissao;

    @Column(name = "SALARIO")
    private Double salario;

    @Column(name = "BENEFICIOS", length = 255)
    private String beneficios;

    @Column(name = "FLAG_ATIVO")
    private Boolean flagAtivo;
}
