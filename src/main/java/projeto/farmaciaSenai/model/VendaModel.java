package projeto.farmaciaSenai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBVENDA")
public class VendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VENDA")
    private Integer idVenda;

    @Column(name = "DATA_VENDA", nullable = false)
    private LocalDateTime dataVenda;


    @Column(name = "VALOR_TOTAL", nullable = false, precision = 14, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "VALOR_PRODUTO", precision = 14, scale = 2)
    private BigDecimal valorProduto;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private ClienteModel clienteVenda;

    @ManyToOne
    @JoinColumn(name = "ID_FUNCIONARIO", nullable = false)
    private FuncionarioModel funcionario;
}
