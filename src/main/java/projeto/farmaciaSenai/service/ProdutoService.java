package projeto.farmaciaSenai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto.farmaciaSenai.dto.ProdutoDto;
import projeto.farmaciaSenai.model.CategoriaProdutoModel;
import projeto.farmaciaSenai.model.ProdutoModel;
import projeto.farmaciaSenai.repository.CategoriaProdutoRepository;
import projeto.farmaciaSenai.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaProdutoRepository categoriaProdutoRepository;


    public ProdutoModel salvarProduto(ProdutoDto dto) {
        CategoriaProdutoModel categoria = categoriaProdutoRepository.findById(dto.idCategoriaProduto())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        ProdutoModel produto = new ProdutoModel();
        produto.setNomeProduto(dto.nomeProduto());
        produto.setDescricaoProduto(dto.descricaoProduto());
        produto.setMedidaProduto(dto.medidaProduto());
        produto.setQuantidadeProduto(dto.quantidadeProduto());
        produto.setValidadeProduto(dto.validadeProduto());
        produto.setCategoriaProduto(categoria);
        produto.setPrecoProduto(dto.precoProduto());
        return produtoRepository.save(produto);
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
            categoria.setIdCategoriaProduto(dto.idCategoriaProduto());
            produto.setPrecoProduto(dto.precoProduto());
            return produtoRepository.save(produto);
        });
    }
}
