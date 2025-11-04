package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.farmaciaSenai.dto.UsuarioDto;
import projeto.farmaciaSenai.model.UsuarioModel;
import projeto.farmaciaSenai.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    private UsuarioDto toDto(UsuarioModel usuarioModel) {
        return new UsuarioDto(
                usuarioModel.getIdUsuario(),
                usuarioModel.getNome(),
                usuarioModel.getEmail(),
                usuarioModel.getCpf(),
                usuarioModel.getSenha(),
                usuarioModel.getTelefone()
        );
    }

    private void copyFromDto(UsuarioDto usuarioDto, UsuarioModel usuarioModel) {
        usuarioModel.setNome(usuarioDto.nome());
        usuarioModel.setEmail(usuarioDto.email());
        usuarioModel.setCpf(usuarioDto.cpf());
        usuarioModel.setSenha(usuarioDto.senha());
        usuarioModel.setTelefone(usuarioDto.telefone());
    }

    @Transactional
    public UsuarioDto salvar(UsuarioDto usuarioDto) {
        UsuarioModel novoUsuario = new UsuarioModel();
        copyFromDto(usuarioDto, novoUsuario);
        UsuarioModel usuarioSalvo = usuarioRepository.save(novoUsuario);
        return toDto(usuarioSalvo);
    }

    public List<UsuarioDto> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<UsuarioDto> buscar(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario).map(this::toDto);
    }

    @Transactional
    public Optional<UsuarioDto> atualizar(Integer idUsuario, UsuarioDto usuarioDto) {
        return usuarioRepository.findById(idUsuario).map(usuarioExistente -> {
            copyFromDto(usuarioDto, usuarioExistente);
            UsuarioModel usuarioAtualizado = usuarioRepository.save(usuarioExistente);
            return toDto(usuarioAtualizado);
        });
    }

    @Transactional
    public boolean deletar(Integer idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) return false;
        usuarioRepository.deleteById(idUsuario);
        return true;
    }
}
