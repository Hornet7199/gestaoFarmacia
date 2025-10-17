package projeto.farmaciaSenai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBVENDA")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VENDA")
    private Integer idVenda;

    @Column(name = "DATA_VENDA", nullable = false)
    private LocalDateTime dataVenda;

    @Column(name = "VALOR_TOTAL", nullable = false)
    private Double valorTotal;

    @Column(name = "VALOR_PRODUTO")
    private Double valorProduto;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private Cliente clienteVenda;

    @ManyToOne
    @JoinColumn(name = "ID_FUNCIONARIO", nullable = false)
    private Funcionario funcionario;
}