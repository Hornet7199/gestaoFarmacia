package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
import projeto.farmaciaSenai.model.ClienteModel;
import projeto.farmaciaSenai.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteModel salvar(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    public List<ClienteModel> listar() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteModel> buscarPorId(Integer idCliente) {
        return clienteRepository.findById(idCliente);
    }

    public Optional<ClienteModel> atualizar(Integer idCliente, ClienteModel dados) {
        return clienteRepository.findById(idCliente).map(cliente -> {
            cliente.setNome(dados.getNome());
            cliente.setEmail(dados.getEmail());
            cliente.setCpf(dados.getCpf());
            cliente.setSenha(dados.getSenha());
            cliente.setTelefone(dados.getTelefone());
            cliente.setTipoPlanoSaude(dados.getTipoPlanoSaude());
            return clienteRepository.save(cliente);
        });
    }

    public boolean deletar(Integer idCliente) {
        if (clienteRepository.existsById(idCliente)) {
            clienteRepository.deleteById(idCliente);
            return true;
        }
        return false;
    }
}
