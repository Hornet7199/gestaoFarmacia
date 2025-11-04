package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.farmaciaSenai.dto.FuncionarioDto;
import projeto.farmaciaSenai.model.FuncionarioModel;
import projeto.farmaciaSenai.model.UsuarioModel;
import projeto.farmaciaSenai.repository.FuncionarioRepository;
import projeto.farmaciaSenai.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final UsuarioRepository usuarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository,
                              UsuarioRepository usuarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    private FuncionarioDto toDto(FuncionarioModel funcionarioModel) {
        Integer usuarioId = funcionarioModel.getUsuario() != null
                ? funcionarioModel.getUsuario().getIdUsuario()
                : null;

        return new FuncionarioDto(
                funcionarioModel.getIdFuncionario(),
                usuarioId,
                funcionarioModel.getNivelAcesso(),
                funcionarioModel.getCargo(),
                funcionarioModel.getDataAdmissao(),
                funcionarioModel.getSalario(),
                funcionarioModel.getBeneficios(),
                funcionarioModel.getFlagAtivo()
        );
    }


    private void copyFromDto(FuncionarioDto funcionarioDto, FuncionarioModel funcionarioModel) {
        funcionarioModel.setNivelAcesso(funcionarioDto.nivelAcesso());
        funcionarioModel.setCargo(funcionarioDto.cargo());
        funcionarioModel.setDataAdmissao(funcionarioDto.dataAdmissao());
        funcionarioModel.setSalario(funcionarioDto.salario());
        funcionarioModel.setBeneficios(funcionarioDto.beneficios());
        funcionarioModel.setFlagAtivo(funcionarioDto.flagAtivo());
    }

    @Transactional
    public Optional<FuncionarioDto> salvar(FuncionarioDto funcionarioDto) {
        Optional<UsuarioModel> usuarioRelacionado = usuarioRepository.findById(funcionarioDto.usuarioId());
        if (usuarioRelacionado.isEmpty()) return Optional.empty();

        FuncionarioModel novoFuncionario = new FuncionarioModel();
        novoFuncionario.setUsuario(usuarioRelacionado.get());
        copyFromDto(funcionarioDto, novoFuncionario);

        FuncionarioModel funcionarioSalvo = funcionarioRepository.save(novoFuncionario);
        return Optional.of(toDto(funcionarioSalvo));
    }

    public List<FuncionarioDto> listar() {
        return funcionarioRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<FuncionarioDto> buscar(Integer idFuncionario) {
        return funcionarioRepository.findById(idFuncionario).map(this::toDto);
    }

    @Transactional
    public Optional<FuncionarioDto> atualizar(Integer idFuncionario, FuncionarioDto funcionarioDto) {
        return funcionarioRepository.findById(idFuncionario).map(funcionarioExistente -> {
            if (funcionarioDto.usuarioId() != null) {
                usuarioRepository.findById(funcionarioDto.usuarioId()).ifPresent(funcionarioExistente::setUsuario);
            }
            copyFromDto(funcionarioDto, funcionarioExistente);
            FuncionarioModel funcionarioAtualizado = funcionarioRepository.save(funcionarioExistente);
            return toDto(funcionarioAtualizado);
        });
    }

    @Transactional
    public boolean deletar(Integer idFuncionario) {
        if (!funcionarioRepository.existsById(idFuncionario)) return false;
        funcionarioRepository.deleteById(idFuncionario);
        return true;
    }
}
