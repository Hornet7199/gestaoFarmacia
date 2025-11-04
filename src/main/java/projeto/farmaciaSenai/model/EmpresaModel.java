package projeto.farmaciaSenai.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "TBEMPRESA")
public class EmpresaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPRESA")
    private Integer idEmpresa;

    @Column(name = "NOME_EMPRESA", nullable = false, length = 50)
    private String nomeEmpresa;

    @Column(name = "NOME_FANTASIA", length = 50)
    private String nomeFantasia;

    @Column(name = "CNPJ", nullable = false, length = 16)
    private String cnpj;
}
