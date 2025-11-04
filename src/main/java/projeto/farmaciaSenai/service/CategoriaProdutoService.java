package projeto.farmaciaSenai.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto.farmaciaSenai.dto.CategoriaProdutoDto;
import projeto.farmaciaSenai.model.CategoriaProdutoModel;
import projeto.farmaciaSenai.repository.CategoriaProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaProdutoService {

    private final CategoriaProdutoRepository categoriaProdutoRepository;

    public CategoriaProdutoService(CategoriaProdutoRepository categoriaProdutoRepository) {
        this.categoriaProdutoRepository = categoriaProdutoRepository;
    }

    private CategoriaProdutoDto toDto(CategoriaProdutoModel categoriaProdutoModel) {
        return new CategoriaProdutoDto(
                categoriaProdutoModel.getIdCategoriaProduto(),
                categoriaProdutoModel.getNomeCategoriaProduto()
        );
    }

    private void copyFromDto(CategoriaProdutoDto categoriaProdutoDto, CategoriaProdutoModel categoriaProdutoModel) {
        categoriaProdutoModel.setNomeCategoriaProduto(categoriaProdutoDto.nomeCategoriaProduto());
    }

    @Transactional
    public CategoriaProdutoDto salvar(CategoriaProdutoDto categoriaProdutoDto) {
        CategoriaProdutoModel novaCategoria = new CategoriaProdutoModel();
        copyFromDto(categoriaProdutoDto, novaCategoria);
        CategoriaProdutoModel categoriaSalva = categoriaProdutoRepository.save(novaCategoria);
        return toDto(categoriaSalva);
    }

    public List<CategoriaProdutoDto> listar() {
        return categoriaProdutoRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Optional<CategoriaProdutoDto> buscar(Integer idCategoria) {
        return categoriaProdutoRepository.findById(idCategoria).map(this::toDto);
    }

    @Transactional
    public Optional<CategoriaProdutoDto> atualizar(Integer idCategoria, CategoriaProdutoDto categoriaProdutoDto) {
        return categoriaProdutoRepository.findById(idCategoria).map(categoriaExistente -> {
            copyFromDto(categoriaProdutoDto, categoriaExistente);
            CategoriaProdutoModel categoriaAtualizada = categoriaProdutoRepository.save(categoriaExistente);
            return toDto(categoriaAtualizada);
        });
    }

    @Transactional
    public boolean deletar(Integer idCategoria) {
        if (!categoriaProdutoRepository.existsById(idCategoria)) return false;
        categoriaProdutoRepository.deleteById(idCategoria);
        return true;
    }
}
