
package projeto.farmaciaSenai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBPRODUTO")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Integer idProduto;

    @Column(name = "NOME_PRODUTO", nullable = false, length = 100)
    private String nomeProduto;

    @Column(name = "DESCRICAO_PRODUTO")
    private String descricaoProduto;

    @Column(name = "PRECO_PRODUTO", nullable = false)
    private Double precoProduto;

    @Column(name = "QUANTIDADE_PRODUTO", nullable = false)
    private Double quantidadeProduto;

    @Column(name = "MEDIDA_PRODUTO", nullable = false, length = 20)
    private String medidaProduto;

    @Column(name = "VALIDADE_PRODUTO",  nullable = false, length = 20)
    private String validadeProduto;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA_PRODUTO", nullable = false)
    private CategoriaProdutoModel categoriaProduto;
}
