// controller/EstoqueController.java
package projeto.farmaciaSenai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.farmaciaSenai.dto.EstoqueDto;
import projeto.farmaciaSenai.service.EstoqueService;

import java.util.List;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping
    public List<EstoqueDto> listar() {
        return estoqueService.listar();
    }

    @GetMapping("/{idEstoque}")
    public ResponseEntity<EstoqueDto> buscar(@PathVariable Long idEstoque) {
        return estoqueService.buscar(idEstoque)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EstoqueDto> criar(@RequestBody EstoqueDto estoqueDto) {
        return estoqueService.salvar(estoqueDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{idEstoque}")
    public ResponseEntity<EstoqueDto> atualizar(@PathVariable Long idEstoque, @RequestBody EstoqueDto estoqueDto) {
        return estoqueService.atualizar(idEstoque, estoqueDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idEstoque}")
    public ResponseEntity<Void> deletar(@PathVariable Long idEstoque) {
        return estoqueService.deletar(idEstoque)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
