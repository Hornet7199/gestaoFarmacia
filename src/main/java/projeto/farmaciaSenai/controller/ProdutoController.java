package projeto.farmaciaSenai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.farmaciaSenai.model.ProdutoModel;
import projeto.farmaciaSenai.service.ProdutoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProdutoModel> criar(@RequestBody ProdutoModel body,
                                              @RequestParam Integer idCategoria) {
        ProdutoModel salvo = service.salvar(body, idCategoria);
        return ResponseEntity.created(URI.create("/api/v1/produtos/" + salvo.getIdProduto())).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> buscar(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoModel> atualizar(@PathVariable Integer id,
                                                  @RequestBody ProdutoModel body,
                                                  @RequestParam Integer idCategoria) {
        return service.atualizar(id, body, idCategoria)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean ok = service.deletar(id);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
