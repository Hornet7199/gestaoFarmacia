package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
import projeto.farmaciaSenai.model.EmpresaModel;
import projeto.farmaciaSenai.repository.EmpresaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public EmpresaModel salvar(EmpresaModel empresa) {
        return empresaRepository.save(empresa);
    }

    public List<EmpresaModel> listar() {
        return empresaRepository.findAll();
    }

    public Optional<EmpresaModel> buscarPorId(Integer idEmpresa) {
        return empresaRepository.findById(idEmpresa);
    }

    public Optional<EmpresaModel> atualizar(Integer idEmpresa, EmpresaModel dados) {
        return empresaRepository.findById(idEmpresa).map(empresa -> {
            empresa.setNomeEmpresa(dados.getNomeEmpresa());
            empresa.setNomeFantasia(dados.getNomeFantasia());
            empresa.setCnpj(dados.getCnpj());
            return empresaRepository.save(empresa);
        });
    }

    public boolean deletar(Integer idEmpresa) {
        if (empresaRepository.existsById(idEmpresa)) {
            empresaRepository.deleteById(idEmpresa);
            return true;
        }
        return false;
    }
}
