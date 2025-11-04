// controller/ClienteController.java
package projeto.farmaciaSenai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.farmaciaSenai.dto.ClienteDto;
import projeto.farmaciaSenai.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDto> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteDto> buscar(@PathVariable Integer idCliente) {
        return clienteService.buscar(idCliente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteDto> criar(@RequestBody ClienteDto clienteDto) {
        return clienteService.salvar(clienteDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<ClienteDto> atualizar(@PathVariable Integer idCliente, @RequestBody ClienteDto clienteDto) {
        return clienteService.atualizar(idCliente, clienteDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> deletar(@PathVariable Integer idCliente) {
        return clienteService.deletar(idCliente)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
