
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
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Integer idProduto;

    @Column(name = "NOME_PRODUTO", nullable = false, length = 100)
    private String nomeProduto;

    @Column(name = "DESCRICAO_PRODUTO", length = 255)
    private String descricaoProduto;

    @Column(name = "PRECO_PRODUTO", nullable = false)
    private Double precoProduto;

    @Column(name = "QUANTIDADE_PRODUTO", nullable = false)
    private Integer quantidadeProduto;

    @Column(name = "MEDIDA_PRODUTO", nullable = false)
    private Integer medidaProduto;

    @Column(name = "VALIDADE_PRODUTO")
    private Integer validadeProduto;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA_PRODUTO", nullable = false)
    private CategoriaProduto categoriaProduto;
}
