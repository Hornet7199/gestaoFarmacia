package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
import projeto.farmaciaSenai.model.CategoriaProdutoModel;
import projeto.farmaciaSenai.repository.CategoriaProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaProdutoService {

    private final CategoriaProdutoRepository categoriaProdutoRepository;

    public CategoriaProdutoService(CategoriaProdutoRepository categoriaProdutoRepository) {
        this.categoriaProdutoRepository = categoriaProdutoRepository;
    }

    public CategoriaProdutoModel salvar(CategoriaProdutoModel categoria) {
        return categoriaProdutoRepository.save(categoria);
    }

    public List<CategoriaProdutoModel> listar() {
        return categoriaProdutoRepository.findAll();
    }

    public Optional<CategoriaProdutoModel> buscarPorId(Integer idCategoria) {
        return categoriaProdutoRepository.findById(idCategoria);
    }

    public Optional<CategoriaProdutoModel> atualizar(Integer idCategoria, CategoriaProdutoModel dados) {
        return categoriaProdutoRepository.findById(idCategoria).map(categoria -> {
            categoria.setNomeCategoriaProduto(dados.getNomeCategoriaProduto());
            return categoriaProdutoRepository.save(categoria);
        });
    }

    public boolean deletar(Integer idCategoria) {
        if (categoriaProdutoRepository.existsById(idCategoria)) {
            categoriaProdutoRepository.deleteById(idCategoria);
            return true;
        }
        return false;
    }
}
