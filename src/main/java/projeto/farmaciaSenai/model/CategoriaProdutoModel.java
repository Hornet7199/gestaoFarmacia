package projeto.farmaciaSenai.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "TBCATPRODUTO")
public class CategoriaProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA_PRODUTO")
    private Integer idCategoriaProduto;

    @Column(name = "NOME_CATEGORIA_PRODUTO", nullable = false, length = 100)
    private String nomeCategoriaProduto;

    @OneToMany(mappedBy = "categoriaProduto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProdutoModel> produtos;
}
