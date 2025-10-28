package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.farmaciaSenai.model.FuncionarioModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Integer> {
    Optional<FuncionarioModel> findByIdUsuario(Integer idUsuario);
    Optional<FuncionarioModel> findByEmailIgnoreCase(String email);
    Optional<FuncionarioModel> findByCpf(String cpf);
    List<FuncionarioModel> findAllByCargoIgnoreCase(String cargo);
    List<FuncionarioModel> findAllByFlagAtivo(Boolean flagAtivo);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByCpf(String cpf);
}
