package projeto.farmaciaSenai.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "TBESTOQUE")
public class EstoqueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESTOQUE")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PRODUTO", nullable = false)
    private ProdutoModel produto;

    @Column(name = "QUANTIDADE_ATUAL", nullable = false)
    private Integer quantidadeAtual;

    @Column(name = "UNIDADE_MEDIDA", length = 20)
    private String unidadeMedida;

    @Column(name = "PRECO_UNITARIO", precision = 12, scale = 2)
    private BigDecimal precoUnitario;

    @Column(name = "LOCALIZACAO", length = 60)
    private String localizacao;

    @Column(name = "CODIGO_BARRAS", length = 64)
    private String codigoBarras;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO", nullable = false)
    private LocalDateTime dataAtualizacao;

    @PrePersist
    private void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        dataCriacao = now;
        dataAtualizacao = now;
        if (quantidadeAtual == null) quantidadeAtual = 0;
    }

    @PreUpdate
    private void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

}
