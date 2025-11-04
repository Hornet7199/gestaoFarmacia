package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.farmaciaSenai.model.VendaModel;

public interface VendaRepository extends JpaRepository<VendaModel, Integer> {

}
