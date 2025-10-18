package projeto.farmaciaSenai.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import projeto.farmaciaSenai.model.CategoriaProdutoModel;

public record ProdutoDto(

        @NotNull(message = "Nome do produto não pode ser nulo")
        @NotBlank(message = "Nome do produto não pode estar em branco")
        String nomeProduto,
        @NotNull(message = "Descrição do produto não pode ser nulo")
        @NotBlank(message = "Descrição do produto não pode estar em branco")
        String descricaoProduto,
        @NotNull(message = "Preço do produto não pode ser nulo")
        @DecimalMin(value = "0.01")
        Double precoProduto,
        @NotNull(message = "Quantidade do produto não pode ser nulo")
        Double quantidadeProduto,
        @NotNull(message = "Medida do produto não pode ser nulo")
        @NotBlank(message = "Medida do produto não pode estar em branco")
        String medidaProduto,
        @NotNull(message = "Validade do produto não pode ser nulo")
        @NotBlank(message = "Validade do produto não pode estar em branco")
        String validadeProduto,
        @NotNull(message = "Categoria do produto não pode ser nulo")
        @NotBlank(message = "Categoria do produto não pode estar em branco")
        CategoriaProdutoModel categoriaProduto
) {}
