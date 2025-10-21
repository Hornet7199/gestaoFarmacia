package projeto.farmaciaSenai.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.farmaciaSenai.dto.ProdutoDto;
import projeto.farmaciaSenai.model.ProdutoModel;
import projeto.farmaciaSenai.service.ProdutoService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoModel> criarProduto(@Valid @RequestBody ProdutoDto dto) {
        ProdutoModel produtoModel = produtoService.salvarProduto(dto);
        return ResponseEntity.ok(produtoModel);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarTodosProdutos(){
        return ResponseEntity.ok(produtoService.listarProdutos());
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoModel> listarPorIdProduto(@PathVariable Integer idProduto){
        return produtoService.buscarPorIdProduto(idProduto).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
