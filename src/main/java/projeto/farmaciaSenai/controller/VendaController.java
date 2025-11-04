package projeto.farmaciaSenai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.farmaciaSenai.dto.VendaDto;
import projeto.farmaciaSenai.service.VendaService;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping
    public List<VendaDto> listar() {
        return vendaService.listar();
    }

    @GetMapping("/{idVenda}")
    public ResponseEntity<VendaDto> buscar(@PathVariable Integer idVenda) {
        return vendaService.buscar(idVenda)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VendaDto> criar(@RequestBody VendaDto vendaDto) {
        return vendaService.salvar(vendaDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{idVenda}")
    public ResponseEntity<VendaDto> atualizar(@PathVariable Integer idVenda, @RequestBody VendaDto vendaDto) {
        return vendaService.atualizar(idVenda, vendaDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idVenda}")
    public ResponseEntity<Void> deletar(@PathVariable Integer idVenda) {
        return vendaService.deletar(idVenda)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
