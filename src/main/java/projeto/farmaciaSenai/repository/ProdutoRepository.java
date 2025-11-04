package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.farmaciaSenai.model.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {}
