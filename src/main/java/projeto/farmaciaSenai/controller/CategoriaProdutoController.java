package projeto.farmaciaSenai.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.farmaciaSenai.dto.CategoriaProdutoDto;
import projeto.farmaciaSenai.model.CategoriaProdutoModel;
import projeto.farmaciaSenai.repository.CategoriaProdutoRepository;
import projeto.farmaciaSenai.service.CategoriaProdutoService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/categoriaProduto")
public class CategoriaProdutoController {

    private final CategoriaProdutoService categoriaService;

    public CategoriaProdutoController(CategoriaProdutoService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaProdutoModel> criar(@Valid @RequestBody CategoriaProdutoDto dto){
        CategoriaProdutoModel categoria = categoriaService.salvar(dto);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProdutoModel>> listarTodos(){
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/{idCategoriaProduto}")
    public ResponseEntity<CategoriaProdutoModel> buscarPorId(@PathVariable Integer idCategoriaProduto){
        return categoriaService.buscarPorId(idCategoriaProduto).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
