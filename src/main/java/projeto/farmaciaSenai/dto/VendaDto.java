package projeto.farmaciaSenai.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public record VendaDto(
        Integer idVenda,
        @NotNull LocalDateTime dataVenda,
        @NotNull @Positive Double valorTotal,
        @Positive Double valorProduto,
        @NotNull Integer clienteId,
        @NotNull Integer funcionarioId
) {}
