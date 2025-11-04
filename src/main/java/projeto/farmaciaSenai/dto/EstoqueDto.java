package projeto.farmaciaSenai.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EstoqueDto(
        Integer id,

        @NotNull Integer produtoId,
        @NotNull @PositiveOrZero Integer quantidadeAtual,
        @Size(max = 20) String unidadeMedida,
        @DecimalMin(value = "0.00") BigDecimal precoUnitario,
        @Size(max = 60) String localizacao,
        @Size(max = 64) String codigoBarras,

        @PastOrPresent LocalDateTime dataCriacao,
        @PastOrPresent LocalDateTime dataAtualizacao
) {}
