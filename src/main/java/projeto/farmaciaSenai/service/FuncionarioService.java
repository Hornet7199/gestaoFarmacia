package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
import projeto.farmaciaSenai.model.FuncionarioModel;
import projeto.farmaciaSenai.repository.FuncionarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public FuncionarioModel salvar(FuncionarioModel funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<FuncionarioModel> listar() {
        return funcionarioRepository.findAll();
    }

    public Optional<FuncionarioModel> buscarPorId(Integer idFuncionario) {
        return funcionarioRepository.findById(idFuncionario);
    }

    public Optional<FuncionarioModel> atualizar(Integer idFuncionario, FuncionarioModel dados) {
        return funcionarioRepository.findById(idFuncionario).map(funcionario -> {
            funcionario.setNome(dados.getNome());
            funcionario.setEmail(dados.getEmail());
            funcionario.setCpf(dados.getCpf());
            funcionario.setSenha(dados.getSenha());
            funcionario.setTelefone(dados.getTelefone());
            funcionario.setCargo(dados.getCargo());
            funcionario.setNivelAcesso(dados.getNivelAcesso());
            funcionario.setSalario(dados.getSalario());
            funcionario.setDataAdmissao(dados.getDataAdmissao());
            funcionario.setBeneficios(dados.getBeneficios());
            funcionario.setFlagAtivo(dados.getFlagAtivo());
            return funcionarioRepository.save(funcionario);
        });
    }

    public boolean deletar(Integer idFuncionario) {
        if (funcionarioRepository.existsById(idFuncionario)) {
            funcionarioRepository.deleteById(idFuncionario);
            return true;
        }
        return false;
    }
}
