package projeto.farmaciaSenai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmpresaDto(

        @NotNull(message = "O nome da empresa não pode ser nulo")
        @NotBlank(message = "O nome da empresa não pode estar em branco")
        String nomeEmpresa,
        String nomeFantasia,
        @NotNull(message = "O CNPJ não pode ser nulo")
        @NotBlank(message = "O CNPJ não pode estar em branco")
        String cnpj
) {}
