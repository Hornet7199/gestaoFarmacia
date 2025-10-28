package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.farmaciaSenai.model.ProdutoModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {
    Optional<ProdutoModel> findByIdProduto(Integer idProduto);
    Optional<ProdutoModel> findByNomeProdutoIgnoreCase(String nomeProduto);
    Optional<ProdutoModel> findByDescricaoProdutoIgnoreCase(String descricaoProduto);
    List<ProdutoModel> findAllByValidadeProduto(String validadeProduto);
    List<ProdutoModel> findAllByCategoriaProduto(Integer categoriaProduto);
}
