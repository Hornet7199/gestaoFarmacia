package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.farmaciaSenai.model.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {}
