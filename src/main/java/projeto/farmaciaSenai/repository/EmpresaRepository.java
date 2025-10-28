// EmpresaRepository.java
package projeto.farmaciaSenai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.farmaciaSenai.model.EmpresaModel;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaModel, Integer> {
    Optional<EmpresaModel> findByIdEmpresa(Integer idEmpresa);
    Optional<EmpresaModel> findByNomeEmpresaIgnoreCase(String nomeEmpresa);
    Optional<EmpresaModel> findByNomeFantasiaIgnoreCase(String nomeFantasia);
    Optional<EmpresaModel> findByCnpj(String cnpj);
}
