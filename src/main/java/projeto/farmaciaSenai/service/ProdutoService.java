package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
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

    public ProdutoModel salvar(ProdutoModel produto) {
        Optional<CategoriaProdutoModel> categoria = categoriaProdutoRepository.findById(idCategoria);
        categoria.ifPresent(produto::setCategoriaProduto);
        return produtoRepository.save(produto);
    }

    public List<ProdutoModel> listar() {
        return produtoRepository.findAll();
    }

    public Optional<ProdutoModel> buscarPorId(Integer idProduto) {
        return produtoRepository.findById(idProduto);
    }

    public Optional<ProdutoModel> atualizar(Integer idProduto, ProdutoModel dados, Integer idCategoria) {
        return produtoRepository.findById(idProduto).map(produto -> {
            produto.setNomeProduto(dados.getNomeProduto());
            produto.setDescricaoProduto(dados.getDescricaoProduto());
            produto.setMedidaProduto(dados.getMedidaProduto());
            produto.setQuantidadeProduto(dados.getQuantidadeProduto());
            produto.setValidadeProduto(dados.getValidadeProduto());
            produto.setPrecoProduto(dados.getPrecoProduto());
            categoriaProdutoRepository.findById(idCategoria).ifPresent(produto::setCategoriaProduto);
            return produtoRepository.save(produto);
        });
    }

    public boolean deletar(Integer idProduto) {
        if (produtoRepository.existsById(idProduto)) {
            produtoRepository.deleteById(idProduto);
            return true;
        }
        return false;
    }
}
