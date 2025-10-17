package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpInc;
import projeto.farmaciaSenai.model.Venda;

import java.util.Optional;

public interface VendaRepository extends JpaRepository<Venda, Integer> {

    Optional<Venda> findById(Integer id);

    Optional<Venda> findByUsuarioId(Integer id);

    Optional<Venda> findByFuncionarioId(Integer id);
}
