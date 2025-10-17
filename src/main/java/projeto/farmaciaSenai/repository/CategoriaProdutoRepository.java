package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.farmaciaSenai.model.CategoriaProduto;

import java.util.Optional;

public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto,Integer> {

    Optional<CategoriaProduto> findByCategoriaId(Integer id);

    Optional<CategoriaProduto> findBynomeCategoria(String nomeCategoria);
}