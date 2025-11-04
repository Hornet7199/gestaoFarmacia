package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.farmaciaSenai.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {}
