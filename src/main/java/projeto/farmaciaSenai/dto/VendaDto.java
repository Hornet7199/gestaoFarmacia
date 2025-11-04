package projeto.farmaciaSenai.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VendaDto(
        Integer idVenda,
        @NotNull LocalDateTime dataVenda,
        @NotNull @DecimalMin("0.00") BigDecimal valorTotal,
        @DecimalMin("0.00") BigDecimal valorProduto,
        @NotNull Integer clienteVendaId,
        @NotNull Integer funcionarioId
) {}
