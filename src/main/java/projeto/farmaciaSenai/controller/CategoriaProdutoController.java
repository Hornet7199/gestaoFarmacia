// controller/CategoriaProdutoController.java
package projeto.farmaciaSenai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.farmaciaSenai.dto.CategoriaProdutoDto;
import projeto.farmaciaSenai.service.CategoriaProdutoService;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaProdutoController {

    private final CategoriaProdutoService categoriaProdutoService;

    public CategoriaProdutoController(CategoriaProdutoService categoriaProdutoService) {
        this.categoriaProdutoService = categoriaProdutoService;
    }

    @GetMapping
    public List<CategoriaProdutoDto> listar() {
        return categoriaProdutoService.listar();
    }

    @GetMapping("/{idCategoriaProduto}")
    public ResponseEntity<CategoriaProdutoDto> buscar(@PathVariable Integer idCategoriaProduto) {
        return categoriaProdutoService.buscar(idCategoriaProduto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CategoriaProdutoDto criar(@RequestBody CategoriaProdutoDto categoriaProdutoDto) {
        return categoriaProdutoService.salvar(categoriaProdutoDto);
    }

    @PutMapping("/{idCategoriaProduto}")
    public ResponseEntity<CategoriaProdutoDto> atualizar(@PathVariable Integer idCategoriaProduto, @RequestBody CategoriaProdutoDto categoriaProdutoDto) {
        return categoriaProdutoService.atualizar(idCategoriaProduto, categoriaProdutoDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idCategoriaProduto}")
    public ResponseEntity<Void> deletar(@PathVariable Integer idCategoriaProduto) {
        return categoriaProdutoService.deletar(idCategoriaProduto)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
