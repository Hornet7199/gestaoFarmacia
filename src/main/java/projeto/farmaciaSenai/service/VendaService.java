package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
import projeto.farmaciaSenai.model.ClienteModel;
import projeto.farmaciaSenai.model.FuncionarioModel;
import projeto.farmaciaSenai.model.VendaModel;
import projeto.farmaciaSenai.repository.ClienteRepository;
import projeto.farmaciaSenai.repository.FuncionarioRepository;
import projeto.farmaciaSenai.repository.VendaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;

    public VendaService(VendaRepository vendaRepository,
                        ClienteRepository clienteRepository,
                        FuncionarioRepository funcionarioRepository) {
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public VendaModel salvar(VendaModel venda, Integer idCliente, Integer idFuncionario) {
        Optional<ClienteModel> cliente = clienteRepository.findById(idCliente);
        Optional<FuncionarioModel> funcionario = funcionarioRepository.findById(idFuncionario);
        cliente.ifPresent(venda::setClienteVenda);
        funcionario.ifPresent(venda::setFuncionario);
        return vendaRepository.save(venda);
    }

    public List<VendaModel> listar() {
        return vendaRepository.findAll();
    }

    public Optional<VendaModel> buscarPorId(Integer idVenda) {
        return vendaRepository.findById(idVenda);
    }

    public Optional<VendaModel> atualizar(Integer idVenda, VendaModel dados, Integer idCliente, Integer idFuncionario) {
        return vendaRepository.findById(idVenda).map(venda -> {
            venda.setDataVenda(dados.getDataVenda());
            venda.setValorTotal(dados.getValorTotal());
            venda.setValorProduto(dados.getValorProduto());
            clienteRepository.findById(idCliente).ifPresent(venda::setClienteVenda);
            funcionarioRepository.findById(idFuncionario).ifPresent(venda::setFuncionario);
            return vendaRepository.save(venda);
        });
    }

    public boolean deletar(Integer idVenda) {
        if (vendaRepository.existsById(idVenda)) {
            vendaRepository.deleteById(idVenda);
            return true;
        }
        return false;
    }
}
