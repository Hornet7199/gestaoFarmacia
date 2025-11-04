package projeto.farmaciaSenai.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record ClienteDto(
        Integer idCliente,
        @NotNull Integer usuarioId,
        @Size(max = 30) String tipoPlanoSaude
) {}
