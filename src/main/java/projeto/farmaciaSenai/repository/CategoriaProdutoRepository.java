package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.farmaciaSenai.model.CategoriaProdutoModel;

import java.util.Optional;

public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProdutoModel,Integer> {

    Optional<CategoriaProdutoModel> findByIdCategoriaProduto(Integer idCategoriaProduto);

    Optional<CategoriaProdutoModel> findBynomeCategoria(String nomeCategoria);
}