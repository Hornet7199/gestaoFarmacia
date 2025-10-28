package projeto.farmaciaSenai.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record ProdutoDto(
        Integer idProduto,
        @NotBlank @Size(max = 100) String nomeProduto,
        @Size(max = 255) String descricaoProduto,
        @Size(max = 50) String medidaProduto,
        @PositiveOrZero Integer quantidadeProduto,
        LocalDate validadeProduto,
        @NotNull @Positive Double precoProduto,
        @NotNull Integer categoriaId
) {}
