package projeto.farmaciaSenai.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteDto(
        Integer idUsuario,
        @NotBlank @Size(max = 100) String nome,
        @NotBlank @Email @Size(max = 100) String email,
        @NotBlank @CPF String cpf,
        @NotBlank String senha,
        @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$", message = "Formato do telefone inválido. Use (99) 99999-9999")
        String telefone,
        @Size(max = 30) String tipoPlanoSaude
) {}
