package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.farmaciaSenai.dto.EstoqueDto;
import projeto.farmaciaSenai.model.EstoqueModel;
import projeto.farmaciaSenai.model.ProdutoModel;
import projeto.farmaciaSenai.repository.EstoqueRepository;
import projeto.farmaciaSenai.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;

    public EstoqueService(EstoqueRepository estoqueRepository,
                          ProdutoRepository produtoRepository) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
    }

    private EstoqueDto toDto(EstoqueModel estoqueModel) {
        Integer produtoId = (estoqueModel.getProduto() != null)
                ? estoqueModel.getProduto().getIdProduto()
                : null;

        return new EstoqueDto(
                estoqueModel.getId(),
                produtoId,
                estoqueModel.getQuantidadeAtual(),
                estoqueModel.getUnidadeMedida(),
                estoqueModel.getPrecoUnitario(),
                estoqueModel.getLocalizacao(),
                estoqueModel.getCodigoBarras(),
                estoqueModel.getDataCriacao(),
                estoqueModel.getDataAtualizacao()
        );
    }


    private void copyFromDto(EstoqueDto estoqueDto, EstoqueModel estoqueModel) {
        estoqueModel.setQuantidadeAtual(estoqueDto.quantidadeAtual());
        estoqueModel.setUnidadeMedida(estoqueDto.unidadeMedida());
        estoqueModel.setPrecoUnitario(estoqueDto.precoUnitario());
        estoqueModel.setLocalizacao(estoqueDto.localizacao());
        estoqueModel.setCodigoBarras(estoqueDto.codigoBarras());
        // datas são gerenciadas pelos @PrePersist/@PreUpdate
    }

    @Transactional
    public Optional<EstoqueDto> salvar(EstoqueDto estoqueDto) {
        Optional<ProdutoModel> produtoRelacionado = produtoRepository.findById(estoqueDto.produtoId());
        if (produtoRelacionado.isEmpty()) return Optional.empty();

        EstoqueModel novoEstoque = new EstoqueModel();
        novoEstoque.setProduto(produtoRelacionado.get());
        copyFromDto(estoqueDto, novoEstoque);

        EstoqueModel estoqueSalvo = estoqueRepository.save(novoEstoque);
        return Optional.of(toDto(estoqueSalvo));
    }

    public List<EstoqueDto> listar() {
        return estoqueRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<EstoqueDto> buscar(Long idEstoque) {
        return estoqueRepository.findById(idEstoque).map(this::toDto);
    }

    @Transactional
    public Optional<EstoqueDto> atualizar(Long idEstoque, EstoqueDto estoqueDto) {
        return estoqueRepository.findById(idEstoque).map(estoqueExistente -> {
            if (estoqueDto.produtoId() != null) {
                produtoRepository.findById(estoqueDto.produtoId()).ifPresent(estoqueExistente::setProduto);
            }
            copyFromDto(estoqueDto, estoqueExistente);
            EstoqueModel estoqueAtualizado = estoqueRepository.save(estoqueExistente);
            return toDto(estoqueAtualizado);
        });
    }

    @Transactional
    public boolean deletar(Long idEstoque) {
        if (!estoqueRepository.existsById(idEstoque)) return false;
        estoqueRepository.deleteById(idEstoque);
        return true;
    }
}
