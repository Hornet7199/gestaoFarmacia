package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.farmaciaSenai.dto.ProdutoDto;
import projeto.farmaciaSenai.model.CategoriaProdutoModel;
import projeto.farmaciaSenai.model.ProdutoModel;
import projeto.farmaciaSenai.repository.CategoriaProdutoRepository;
import projeto.farmaciaSenai.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaProdutoRepository categoriaProdutoRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          CategoriaProdutoRepository categoriaProdutoRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaProdutoRepository = categoriaProdutoRepository;
    }

    private ProdutoDto toDto(ProdutoModel produtoModel) {
        Integer idCategoria = produtoModel.getCategoriaProduto() != null
                ? produtoModel.getCategoriaProduto().getIdCategoriaProduto()
                : null;

        return new ProdutoDto(
                produtoModel.getIdProduto(),
                produtoModel.getNomeProduto(),
                produtoModel.getDescricaoProduto(),
                produtoModel.getMedidaProduto(),
                produtoModel.getQuantidadeProduto(),
                produtoModel.getValidadeProduto(),
                produtoModel.getPrecoProduto(),
                idCategoria
        );
    }

    private void copyFromDto(ProdutoDto produtoDto, ProdutoModel produtoModel) {
        produtoModel.setNomeProduto(produtoDto.nomeProduto());
        produtoModel.setDescricaoProduto(produtoDto.descricaoProduto());
        produtoModel.setMedidaProduto(produtoDto.medidaProduto());
        produtoModel.setQuantidadeProduto(produtoDto.quantidadeProduto());
        produtoModel.setValidadeProduto(produtoDto.validadeProduto());
        produtoModel.setPrecoProduto(produtoDto.precoProduto());
    }

    @Transactional
    public Optional<ProdutoDto> salvar(ProdutoDto produtoDto) {
        Optional<CategoriaProdutoModel> categoriaRelacionada =
                categoriaProdutoRepository.findById(produtoDto.idCategoriaProduto());
        if (categoriaRelacionada.isEmpty()) return Optional.empty();

        ProdutoModel novoProduto = new ProdutoModel();
        copyFromDto(produtoDto, novoProduto);
        novoProduto.setCategoriaProduto(categoriaRelacionada.get());

        ProdutoModel produtoSalvo = produtoRepository.save(novoProduto);
        return Optional.of(toDto(produtoSalvo));
    }

    public List<ProdutoDto> listar() {
        return produtoRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<ProdutoDto> buscar(Integer idProduto) {
        return produtoRepository.findById(idProduto).map(this::toDto);
    }

    @Transactional
    public Optional<ProdutoDto> atualizar(Integer idProduto, ProdutoDto produtoDto) {
        return produtoRepository.findById(idProduto).map(produtoExistente -> {
            copyFromDto(produtoDto, produtoExistente);
            if (produtoDto.idCategoriaProduto() != null) {
                categoriaProdutoRepository.findById(produtoDto.idCategoriaProduto())
                        .ifPresent(produtoExistente::setCategoriaProduto);
            }
            ProdutoModel produtoAtualizado = produtoRepository.save(produtoExistente);
            return toDto(produtoAtualizado);
        });
    }

    @Transactional
    public boolean deletar(Integer idProduto) {
        if (!produtoRepository.existsById(idProduto)) return false;
        produtoRepository.deleteById(idProduto);
        return true;
    }
}
