package projeto.farmaciaSenai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.farmaciaSenai.model.VendaModel;
import projeto.farmaciaSenai.service.VendaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vendas")
public class VendaController {

    private final VendaService service;

    public VendaController(VendaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VendaModel> criar(@RequestBody VendaModel body,
                                            @RequestParam Integer idCliente,
                                            @RequestParam Integer idFuncionario) {
        VendaModel salvo = service.salvar(body, idCliente, idFuncionario);
        return ResponseEntity.created(URI.create("/api/v1/vendas/" + salvo.getIdVenda())).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<VendaModel>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaModel> buscar(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaModel> atualizar(@PathVariable Integer id,
                                                @RequestBody VendaModel body,
                                                @RequestParam Integer idCliente,
                                                @RequestParam Integer idFuncionario) {
        return service.atualizar(id, body, idCliente, idFuncionario)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean ok = service.deletar(id);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
