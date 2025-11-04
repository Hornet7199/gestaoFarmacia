package projeto.farmaciaSenai.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;

public record FuncionarioDto(
        Integer idFuncionario,

        @NotNull Integer usuarioId,
        @Size(max = 20) String nivelAcesso,
        @Size(max = 50) String cargo,
        @PastOrPresent LocalDate dataAdmissao,

        @PositiveOrZero BigDecimal salario,
        @Size(max = 255) String beneficios,
        Boolean flagAtivo
) {}
