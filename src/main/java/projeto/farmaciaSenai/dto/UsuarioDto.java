package projeto.farmaciaSenai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record UsuarioDto(

        @NotNull(message = "Nome não pode ser nulo.")
        @NotBlank(message = "Nome não pode estar em branco.")
        String nome,
        @NotNull(message = "Email não pode ser nulo.")
        @NotBlank(message = "Email não pode estar em branco.")
        String email,
        @NotNull(message = "CPF não pode ser nulo.")
        @NotBlank(message = "CPF não pode estar em branco.")
        @CPF
        String cpf,
        @NotNull(message = "Senha não pode ser nulo.")
        @NotBlank(message = "Senha não pode estar em branco.")
        String senha,
        @NotNull(message = "Telefone não pode ser nulo.")
        @NotBlank(message = "Telefone não pode estar em branco.")
        String telefone


) {}
