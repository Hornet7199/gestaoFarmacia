package projeto.farmaciaSenai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaProdutoDto(
        @NotNull(message = "Nome de categoria não pode ser nulo")
        @NotBlank(message = "Nome da categoria não pode estar em branco")
        String nomeCategoriaProduto,
        Integer idCategoriaProduto

) {}
