package projeto.farmaciaSenai.service;


import org.springframework.stereotype.Service;
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

   public CategoriaProdutoModel salvar(CategoriaProdutoDto dto){
        CategoriaProdutoModel categoriaProduto = new CategoriaProdutoModel();
        categoriaProduto.setNomeCategoriaProduto(dto.nomeCategoriaProduto());
       return  categoriaProdutoRepository.save(categoriaProduto);
    }
   public CategoriaProdutoModel atualizar(CategoriaProdutoDto dto){
        CategoriaProdutoModel categoriaProduto = new CategoriaProdutoModel();
        categoriaProduto.setNomeCategoriaProduto(dto.nomeCategoriaProduto());
        return  categoriaProdutoRepository.save(categoriaProduto);
   }
   public List<CategoriaProdutoModel> listarCategorias(){
        return  categoriaProdutoRepository.findAll();
   }

   public Optional<CategoriaProdutoModel> buscarPorId(Integer idCategoriaProduto){
        return categoriaProdutoRepository.findById(idCategoriaProduto);
   }
   public Optional<CategoriaProdutoModel> buscarPorNome(String nomeCategoriaProduto){
        return categoriaProdutoRepository.findBynomeCategoria(nomeCategoriaProduto);
   }

}
