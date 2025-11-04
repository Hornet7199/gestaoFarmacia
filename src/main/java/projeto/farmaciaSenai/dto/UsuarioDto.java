package projeto.farmaciaSenai.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record UsuarioDto(
        Integer idUsuario,

        @NotBlank @Size(max = 100) String nome,
        @NotBlank @Email @Size(max = 100) String email,
        @NotBlank @CPF @Size(max = 14) String cpf,
        @NotBlank @Size(min = 6, max = 60) String senha,

        @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$",
                message = "Formato do telefone inválido. Use (99) 99999-9999")
        @Size(max = 20) String telefone
) {}
