package projeto.farmaciaSenai.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.farmaciaSenai.model.EmpresaModel;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaModel, Integer> {
    Optional<EmpresaModel> findByIdEmpresa(Integer idEmpresa);
    Optional<EmpresaModel> findByNomeEmpresa(String nomeEmpresa);
    Optional<EmpresaModel> findByNomeFantasia(String nomeFantasia);
    Optional<EmpresaModel> findByCnpj(String cnpj);



    @Transactional
    Optional<EmpresaModel> deleteByIdEmpresa(Integer idEmpresa);

}
