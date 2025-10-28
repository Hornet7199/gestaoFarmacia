package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.farmaciaSenai.model.ClienteModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {
    Optional<ClienteModel> findByIdUsuario(Integer idUsuario);
    Optional<ClienteModel> findByEmailIgnoreCase(String email);
    Optional<ClienteModel> findByCpf(String cpf);
    List<ClienteModel> findAllByTipoPlanoSaudeIgnoreCase(String tipoPlanoSaude);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByCpf(String cpf);
}
