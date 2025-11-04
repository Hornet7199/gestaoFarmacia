package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.farmaciaSenai.model.EmpresaModel;

public interface EmpresaRepository extends JpaRepository<EmpresaModel, Integer> {}
