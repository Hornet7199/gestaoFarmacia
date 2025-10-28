package projeto.farmaciaSenai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.farmaciaSenai.model.CategoriaProdutoModel;
import projeto.farmaciaSenai.service.CategoriaProdutoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaProdutoController {

    private final CategoriaProdutoService service;

    public CategoriaProdutoController(CategoriaProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoriaProdutoModel> criar(@RequestBody CategoriaProdutoModel body) {
        CategoriaProdutoModel salvo = service.salvar(body);
        return ResponseEntity.created(URI.create("/api/v1/categorias/" + salvo.getIdCategoriaProduto())).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProdutoModel>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProdutoModel> buscar(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProdutoModel> atualizar(@PathVariable Integer id, @RequestBody CategoriaProdutoModel body) {
        return service.atualizar(id, body)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean ok = service.deletar(id);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
