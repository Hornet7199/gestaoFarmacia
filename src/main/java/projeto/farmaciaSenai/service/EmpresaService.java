package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.farmaciaSenai.dto.EmpresaDto;
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

    private EmpresaDto toDto(EmpresaModel empresaModel) {
        return new EmpresaDto(
                empresaModel.getIdEmpresa(),
                empresaModel.getNomeEmpresa(),
                empresaModel.getNomeFantasia(),
                empresaModel.getCnpj()
        );
    }

    private void copyFromDto(EmpresaDto empresaDto, EmpresaModel empresaModel) {
        empresaModel.setNomeEmpresa(empresaDto.nomeEmpresa());
        empresaModel.setNomeFantasia(empresaDto.nomeFantasia());
        empresaModel.setCnpj(empresaDto.cnpj());
    }

    @Transactional
    public EmpresaDto salvar(EmpresaDto empresaDto) {
        EmpresaModel novaEmpresa = new EmpresaModel();
        copyFromDto(empresaDto, novaEmpresa);
        EmpresaModel empresaSalva = empresaRepository.save(novaEmpresa);
        return toDto(empresaSalva);
    }

    public List<EmpresaDto> listar() {
        return empresaRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<EmpresaDto> buscar(Integer idEmpresa) {
        return empresaRepository.findById(idEmpresa).map(this::toDto);
    }

    @Transactional
    public Optional<EmpresaDto> atualizar(Integer idEmpresa, EmpresaDto empresaDto) {
        return empresaRepository.findById(idEmpresa).map(empresaExistente -> {
            copyFromDto(empresaDto, empresaExistente);
            EmpresaModel empresaAtualizada = empresaRepository.save(empresaExistente);
            return toDto(empresaAtualizada);
        });
    }

    @Transactional
    public boolean deletar(Integer idEmpresa) {
        if (!empresaRepository.existsById(idEmpresa)) return false;
        empresaRepository.deleteById(idEmpresa);
        return true;
    }
}
