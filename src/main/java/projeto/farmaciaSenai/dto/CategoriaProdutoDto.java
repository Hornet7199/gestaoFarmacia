package projeto.farmaciaSenai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaProdutoDto(
        Integer idCategoriaProduto,
        @NotBlank @Size(max = 100) String nomeCategoriaProduto
) {}
