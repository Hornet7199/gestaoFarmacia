package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.farmaciaSenai.model.VendaModel;

import java.util.Optional;

public interface VendaRepository extends JpaRepository<VendaModel, Integer> {

    Optional<VendaModel> findById(Integer id);

    Optional<VendaModel> findByUsuarioId(Integer id);

    Optional<VendaModel> findByFuncionarioId(Integer id);
}