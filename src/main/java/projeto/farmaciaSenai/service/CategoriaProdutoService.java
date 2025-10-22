package projeto.farmaciaSenai.service;


import org.springframework.stereotype.Service;
import projeto.farmaciaSenai.dto.CategoriaProdutoDto;
import projeto.farmaciaSenai.model.CategoriaProdutoModel;
import projeto.farmaciaSenai.repository.CategoriaProdutoRepository;

@Service
public class CategoriaProdutoService {
    private final CategoriaProdutoRepository categoriaProdutoRepository;

    public CategoriaProdutoService(CategoriaProdutoRepository categoriaProdutoRepository) {
        this.categoriaProdutoRepository = categoriaProdutoRepository;
    }

         public CategoriaProdutoModel salvar(CategoriaProdutoDto dto){
        CategoriaProdutoModel categoriaProduto = new CategoriaProdutoModel();
        categoriaProduto.setNomeCategoriaProduto(dto.nomeCategoriaProduto());
        categoriaProduto.setProdutos();

    }


}
