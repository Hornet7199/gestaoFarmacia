package projeto.farmaciaSenai.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.farmaciaSenai.dto.ProdutoDto;
import projeto.farmaciaSenai.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<ProdutoDto> listar() {
        return produtoService.listar();
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoDto> buscar(@PathVariable Integer idProduto) {
        return produtoService.buscar(idProduto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> salvar(@Valid @RequestBody ProdutoDto produtoDto) {
        return produtoService.salvar(produtoDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{idProduto}")
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Integer idProduto,
                                                @Valid @RequestBody ProdutoDto produtoDto) {
        return produtoService.atualizar(idProduto, produtoDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Void> deletar(@PathVariable Integer idProduto) {
        return produtoService.deletar(idProduto)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
