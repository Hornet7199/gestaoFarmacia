package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.farmaciaSenai.model.FuncionarioModel;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Integer> {}
