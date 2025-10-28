package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
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

    public UsuarioModel salvar(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioModel> listar() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> buscarPorId(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public Optional<UsuarioModel> atualizar(Integer idUsuario, UsuarioModel dados) {
        return usuarioRepository.findById(idUsuario).map(usuario -> {
            usuario.setNome(dados.getNome());
            usuario.setEmail(dados.getEmail());
            usuario.setCpf(dados.getCpf());
            usuario.setSenha(dados.getSenha());
            usuario.setTelefone(dados.getTelefone());
            return usuarioRepository.save(usuario);
        });
    }

    public boolean deletar(Integer idUsuario) {
        if (usuarioRepository.existsById(idUsuario)) {
            usuarioRepository.deleteById(idUsuario);
            return true;
        }
        return false;
    }
}
