package projeto.farmaciaSenai.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import java.time.LocalDate;

public record FuncionarioDto(
        Integer idUsuario,
        @NotBlank @Size(max = 100) String nome,
        @NotBlank @Email @Size(max = 100) String email,
        @NotBlank @CPF String cpf,
        @NotBlank String senha,
        @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$", message = "Formato do telefone inválido. Use (99) 99999-9999")
        String telefone,
        @Size(max = 20) String nivelAcesso,
        @Size(max = 50) String cargo,
        LocalDate dataAdmissao,
        @PositiveOrZero Double salario,
        @Size(max = 255) String beneficios,
        Boolean flagAtivo
) {}
