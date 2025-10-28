package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.farmaciaSenai.model.UsuarioModel;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    Optional<UsuarioModel> findByIdUsuario(Integer idUsuario);
    Optional<UsuarioModel> findByNomeIgnoreCase(String nome);
    Optional<UsuarioModel> findByEmailIgnoreCase(String email);
    Optional<UsuarioModel> findByCpf(String cpf);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByCpf(String cpf);
}
