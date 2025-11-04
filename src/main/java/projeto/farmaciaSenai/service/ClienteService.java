package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.farmaciaSenai.dto.ClienteDto;
import projeto.farmaciaSenai.model.ClienteModel;
import projeto.farmaciaSenai.model.ProdutoModel;
import projeto.farmaciaSenai.model.UsuarioModel;
import projeto.farmaciaSenai.repository.ClienteRepository;
import projeto.farmaciaSenai.repository.ProdutoRepository;
import projeto.farmaciaSenai.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    public ClienteService(ClienteRepository clienteRepository,
                          UsuarioRepository usuarioRepository,
                          ProdutoRepository produtoRepository) {
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    }

    private ClienteDto toDto(ClienteModel clienteModel) {
        List<Integer> historicoIds = new ArrayList<>();
        if (clienteModel.getHistoricoCompras() != null) {
            historicoIds = clienteModel.getHistoricoCompras()
                    .stream()
                    .map(ProdutoModel::getIdProduto)
                    .toList();
        }

        Integer usuarioId = null;
        if (clienteModel.getUsuario() != null) {
            usuarioId = clienteModel.getUsuario().getIdUsuario();
        }

        return new ClienteDto(
                clienteModel.getIdCliente(),
                usuarioId,
                clienteModel.getTipoPlanoSaude()
        );
    }

    @Transactional
    public Optional<ClienteDto> salvar(ClienteDto clienteDto) {
        Optional<UsuarioModel> usuarioRelacionado = usuarioRepository.findById(clienteDto.usuarioId());
        if (usuarioRelacionado.isEmpty()) return Optional.empty();

        ClienteModel novoCliente = new ClienteModel();
        novoCliente.setUsuario(usuarioRelacionado.get());

        ClienteModel clienteSalvo = clienteRepository.save(novoCliente);
        return Optional.of(toDto(clienteSalvo));
    }

    public List<ClienteDto> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<ClienteDto> buscar(Integer idCliente) {
        return clienteRepository.findById(idCliente).map(this::toDto);
    }

    @Transactional
    public Optional<ClienteDto> atualizar(Integer idCliente, ClienteDto clienteDto) {
        return clienteRepository.findById(idCliente).map(clienteExistente -> {
            if (clienteDto.usuarioId() != null) {
                usuarioRepository.findById(clienteDto.usuarioId()).ifPresent(clienteExistente::setUsuario);
            }
            ClienteModel clienteAtualizado = clienteRepository.save(clienteExistente);
            return toDto(clienteAtualizado);
        });
    }

    @Transactional
    public boolean deletar(Integer idCliente) {
        if (!clienteRepository.existsById(idCliente)) return false;
        clienteRepository.deleteById(idCliente);
        return true;
    }
}
