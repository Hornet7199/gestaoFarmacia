package projeto.farmaciaSenai.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.farmaciaSenai.model.CategoriaProdutoModel;
import projeto.farmaciaSenai.model.ProdutoModel;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel,Integer> {

    Optional<ProdutoModel> findByIdProduto(Integer idProduto);
    Optional<ProdutoModel> findByNomeProdutoIgnoreCase(String nomeProduto);
    Optional<ProdutoModel> findByDescricaoProdutoIgnoreCase(String descricaoProduto);
    Optional<ProdutoModel> findByValidadeProduto(String validadeProduto);
    Optional<ProdutoModel> findByCategoriaProduto(Integer categoriaProduto);

    @Transactional
    Optional<ProdutoModel> deleteByIdProduto(Integer idProduto);

}