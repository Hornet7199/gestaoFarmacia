package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.farmaciaSenai.model.EstoqueModel;

public interface EstoqueRepository extends JpaRepository<EstoqueModel, Long> {}
