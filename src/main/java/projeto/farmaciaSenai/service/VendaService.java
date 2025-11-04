package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.farmaciaSenai.dto.VendaDto;
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

    private VendaDto toDto(VendaModel vendaModel) {
        return new VendaDto(
                vendaModel.getIdVenda(),
                vendaModel.getDataVenda(),
                vendaModel.getValorTotal(),
                vendaModel.getValorProduto(),
                vendaModel.getClienteVenda().getIdCliente(),
                vendaModel.getFuncionario().getIdFuncionario()
        );
    }

    private void copyFromDto(VendaDto vendaDto, VendaModel vendaModel) {
        vendaModel.setDataVenda(vendaDto.dataVenda());
        vendaModel.setValorTotal(vendaDto.valorTotal());
        vendaModel.setValorProduto(vendaDto.valorProduto());
    }

    @Transactional
    public Optional<VendaDto> salvar(VendaDto vendaDto) {
        Optional<ClienteModel> clienteRelacionado = clienteRepository.findById(vendaDto.clienteVendaId());
        Optional<FuncionarioModel> funcionarioRelacionado = funcionarioRepository.findById(vendaDto.funcionarioId());
        if (clienteRelacionado.isEmpty() || funcionarioRelacionado.isEmpty()) return Optional.empty();

        VendaModel novaVenda = new VendaModel();
        copyFromDto(vendaDto, novaVenda);
        novaVenda.setClienteVenda(clienteRelacionado.get());
        novaVenda.setFuncionario(funcionarioRelacionado.get());

        VendaModel vendaSalva = vendaRepository.save(novaVenda);
        return Optional.of(toDto(vendaSalva));
    }

    public List<VendaDto> listar() {
        return vendaRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<VendaDto> buscar(Integer idVenda) {
        return vendaRepository.findById(idVenda).map(this::toDto);
    }

    @Transactional
    public Optional<VendaDto> atualizar(Integer idVenda, VendaDto vendaDto) {
        return vendaRepository.findById(idVenda).map(vendaExistente -> {
            copyFromDto(vendaDto, vendaExistente);
            if (vendaDto.clienteVendaId() != null) {
                clienteRepository.findById(vendaDto.clienteVendaId()).ifPresent(vendaExistente::setClienteVenda);
            }
            if (vendaDto.funcionarioId() != null) {
                funcionarioRepository.findById(vendaDto.funcionarioId()).ifPresent(vendaExistente::setFuncionario);
            }
            VendaModel vendaAtualizada = vendaRepository.save(vendaExistente);
            return toDto(vendaAtualizada);
        });
    }

    @Transactional
    public boolean deletar(Integer idVenda) {
        if (!vendaRepository.existsById(idVenda)) return false;
        vendaRepository.deleteById(idVenda);
        return true;
    }
}
