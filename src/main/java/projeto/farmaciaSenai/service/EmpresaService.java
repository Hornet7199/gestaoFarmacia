package projeto.farmaciaSenai.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.farmaciaSenai.dto.EmpresaDto;
import projeto.farmaciaSenai.exception.ExceptionExistente;
import projeto.farmaciaSenai.model.EmpresaModel;
import projeto.farmaciaSenai.repository.EmpresaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public EmpresaModel salvar(EmpresaDto empresaDto) {
        if (empresaRepository.findByIdEmpresa(empresaDto.idEmpresa()).isPresent()) {
            throw new ExceptionExistente("Empresa Existente");

        } else {
            EmpresaModel empresa = new EmpresaModel();
            empresa.setNomeEmpresa(empresaDto.nomeEmpresa());
            empresa.setNomeFantasia(empresaDto.nomeFantasia());
            empresa.setCnpj(empresaDto.cnpj());
            return empresaRepository.save(empresa);

        }
    }

    public List<EmpresaModel> listarEmpresas() {
        return empresaRepository.findAll();
    }

    public Optional<EmpresaModel> buscarEmpresaPorNome(String nomeEmpresa) {
        return empresaRepository.findByNomeEmpresa(nomeEmpresa);
    }

    public Optional<EmpresaModel> buscarPorIdEmpresa(Integer idEmpresa) {
        return empresaRepository.findById(idEmpresa);
    }

    public Optional<EmpresaModel> buscarEmpresaPorCnpj(String cnpj) {
        return empresaRepository.findByCnpj(cnpj);
    }

    public Optional<EmpresaModel> buscarPorNomeFantasia(String nomeFantasia) {
        return empresaRepository.findByNomeFantasia(nomeFantasia);
    }

    public Optional<EmpresaModel> atualizarDadosEmpresa(Integer idEmpresa, EmpresaDto empresaDto) {
        return empresaRepository.findByIdEmpresa(idEmpresa).map(empresa -> {
            empresa.setNomeEmpresa(empresaDto.nomeEmpresa());
            empresa.setNomeFantasia(empresaDto.nomeFantasia());
            empresa.setCnpj(empresaDto.cnpj());
            return empresaRepository.save(empresa);
        });
    }
}
