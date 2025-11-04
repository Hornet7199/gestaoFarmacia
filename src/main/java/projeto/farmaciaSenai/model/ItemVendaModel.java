package projeto.farmaciaSenai.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "TBITEM_VENDA")
public class ItemVendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ITEM_VENDA")
    private Integer idItemVenda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_VENDA", nullable = false)
    private VendaModel venda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PRODUTO", nullable = false)
    private ProdutoModel produto;

    @Column(name = "QUANTIDADE", nullable = false)
    private Integer quantidade;

    @Column(name = "PRECO_UNITARIO", nullable = false, precision = 14, scale = 2)
    private BigDecimal precoUnitario;

    @Column(name = "SUBTOTAL", nullable = false, precision = 14, scale = 2)
    private BigDecimal subtotal;
}
