package projeto.farmaciaSenai.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "TBPRODUTO")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Integer idProduto;

    @Column(name = "NOME_PRODUTO", nullable = false, length = 100)
    private String nomeProduto;

    @Column(name = "DESCRICAO_PRODUTO", length = 255)
    private String descricaoProduto;

    @Column(name = "MEDIDA_PRODUTO", length = 50)
    private String medidaProduto;

    @Column(name = "QUANTIDADE_PRODUTO")
    private Integer quantidadeProduto;

    @Column(name = "VALIDADE_PRODUTO")
    private LocalDate validadeProduto;

    @Column(name = "PRECO_PRODUTO", nullable = false, precision = 14, scale = 2)
    private BigDecimal precoProduto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CATEGORIA_PRODUTO", nullable = false)
    private CategoriaProdutoModel categoriaProduto;
}
