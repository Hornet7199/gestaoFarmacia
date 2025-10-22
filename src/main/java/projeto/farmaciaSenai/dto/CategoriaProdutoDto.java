package projeto.farmaciaSenai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import projeto.farmaciaSenai.model.CategoriaProdutoModel;

import java.util.List;

public record CategoriaProdutoDto(
        @NotNull(message = "Nome de categoria não pode ser nulo")
        @NotBlank(message = "Nome da categoria não pode estar em branco")
        String nomeCategoriaProduto,
        @NotNull(message = "O id da categoria do produto não pode ser nulo")
        @NotBlank(message = "O id da categoria do produto não pode estar em branco")
        Integer idCategoriaProduto,
        @NotNull(message = "A lista de categorias não pode ser nula")
        @NotEmpty(message = "A lista de categorias não pode estar vazia")
        List<CategoriaProdutoModel> categoriaProduto

) {}
