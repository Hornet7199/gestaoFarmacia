package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
import projeto.farmaciaSenai.dto.UsuarioDto;
import projeto.farmaciaSenai.model.UsuarioModel;
import projeto.farmaciaSenai.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService{
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioModel salvar(UsuarioDto dto){
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setCpf(dto.cpf());
        usuario.setTelefone(dto.telefone());
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioModel> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> buscarPorNome(String nome){
        return usuarioRepository.findByNome(nome);
    }

    public Optional<UsuarioModel> buscarPorCpf(String cpf){
        return usuarioRepository.findByCpf(cpf);
    }

    public Optional<UsuarioModel> buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }
    public Optional<UsuarioModel> buscarPorTelefone(String telefone){
        return usuarioRepository.findByTelefone(telefone);
    }

    public Optional<UsuarioModel> buscarPorId(Integer idUsuario){
        return usuarioRepository.findById(idUsuario);
    }

    public Optional<UsuarioModel> atualizarDados(Integer idUsuario, UsuarioDto dto){
        return usuarioRepository.findByIdUsuario(idUsuario).map(usuario -> {
            usuario.setNome(dto.nome());
            usuario.setEmail(dto.email());
            usuario.setTelefone(dto.telefone());
            usuario.setCpf(dto.cpf());
            usuario.setSenha(dto.senha());
            return usuarioRepository.save(usuario);
        });
    }

}