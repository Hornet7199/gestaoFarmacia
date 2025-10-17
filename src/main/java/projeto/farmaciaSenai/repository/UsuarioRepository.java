package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioRepository,Integer> {

    Optional<UsuarioRepository> findByNome(String nome);

    Optional<UsuarioRepository> findByEmail(String email);

    Optional<UsuarioRepository> findByCpf(String cpf);

}