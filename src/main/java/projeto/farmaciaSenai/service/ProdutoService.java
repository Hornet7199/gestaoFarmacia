package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
import projeto.farmaciaSenai.dto.ProdutoDto;
import projeto.farmaciaSenai.model.ProdutoModel;
import projeto.farmaciaSenai.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final ExceptionProdutoExistente exception;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, ExceptionProdutoExistente exception) {
        this.produtoRepository = produtoRepository;
        this.exception = exception;
    }

    public ProdutoModel salvarProduto(ProdutoDto dto) {
        if (produtoRepository.findByIdProduto(dto.idProduto()).isPresent()) {
            throw new ExceptionProdutoExistente("Produto existente");
        } else {
            ProdutoModel produto = new ProdutoModel();
            produto.setNomeProduto(dto.nomeProduto());
            produto.setDescricaoProduto(dto.descricaoProduto());
            produto.setMedidaProduto(dto.medidaProduto());
            produto.setQuantidadeProduto(dto.quantidadeProduto());
            produto.setValidadeProduto(dto.validadeProduto());
            produto.setCategoriaProduto(dto.categoriaProduto());
            produto.setPrecoProduto(dto.precoProduto());
            return produtoRepository.save(produto);
        }
    }


    public List<ProdutoModel> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<ProdutoModel> buscarPorIdProduto(Integer idProduto) {
        return produtoRepository.findById(idProduto);
    }

    public Optional<ProdutoModel> buscarPorNomeProduto(String nomeProduto) {
        return produtoRepository.findByNomeProdutoIgnoreCase(nomeProduto);
    }

    public Optional<ProdutoModel> buscarPorDescricaoProduto(String descricaoProduto) {
        return produtoRepository.findByDescricaoProdutoIgnoreCase(descricaoProduto);
    }

    public Optional<ProdutoModel> buscarPorValidadeProduto(String validadeProduto) {
        return produtoRepository.findByValidadeProduto(validadeProduto);
    }

    public Optional<ProdutoModel> buscarPorCategoriaProduto(Integer categoriaProduto) {
        return produtoRepository.findByCategoriaProduto(categoriaProduto);
    }

    public Optional atualizarProduto(Integer idProduto, ProdutoDto dto) {
        CategoriaProdutoModel categoria = categoriaProdutoRepository.findById(dto.idCategoriaProduto())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));


        return produtoRepository.findByIdProduto(idProduto).map(produto -> {
            produto.setNomeProduto(dto.nomeProduto());
            produto.setDescricaoProduto(dto.descricaoProduto());
            produto.setMedidaProduto(dto.medidaProduto());
            produto.setQuantidadeProduto(dto.quantidadeProduto());
            produto.setValidadeProduto(dto.validadeProduto());
            produto.setCategoriaProduto(dto.categoriaProduto());
            produto.setPrecoProduto(dto.precoProduto());
            return produtoRepository.save(produto);
        });
    }
}
