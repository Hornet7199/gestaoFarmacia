package projeto.farmaciaSenai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record EmpresaDto(
        Integer idEmpresa,
        @NotBlank @Size(max = 100) String nomeEmpresa,
        @NotBlank @Size(max = 100) String nomeFantasia,
        @NotBlank @CNPJ String cnpj
) {}
