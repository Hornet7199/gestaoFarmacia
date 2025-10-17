package projeto.farmaciaSenai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "TBCATPRODUTO")
public class CategoriaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA_PRODUTO")
    private Integer idCategoriaProduto;

    @Column(name = "NOME_CATEGORIA_PRODUTO", nullable = false, length = 100)
    private String nomeCategoriaProduto;

    @OneToMany(mappedBy = "categoriaProduto", cascade = CascadeType.ALL)
    private List<Produto> produtos;
}
