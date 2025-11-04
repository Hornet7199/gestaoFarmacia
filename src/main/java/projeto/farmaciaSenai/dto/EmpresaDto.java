package projeto.farmaciaSenai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmpresaDto(
        Integer idEmpresa,
        @NotBlank @Size(max = 50) String nomeEmpresa,
        @Size(max = 50) String nomeFantasia,
        @NotBlank @Size(max = 16) String cnpj
) {}
