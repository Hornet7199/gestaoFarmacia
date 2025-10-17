package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.farmaciaSenai.model.Produto;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

    Optional<Produto> findByNomeIgnoreCase(String nome);

    Optional<Produto> findByDescricaoIgnoreCase(String descricao);

}
