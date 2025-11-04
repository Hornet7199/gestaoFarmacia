// controller/EmpresaController.java
package projeto.farmaciaSenai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.farmaciaSenai.dto.EmpresaDto;
import projeto.farmaciaSenai.service.EmpresaService;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public List<EmpresaDto> listar() {
        return empresaService.listar();
    }

    @GetMapping("/{idEmpresa}")
    public ResponseEntity<EmpresaDto> buscar(@PathVariable Integer idEmpresa) {
        return empresaService.buscar(idEmpresa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EmpresaDto criar(@RequestBody EmpresaDto empresaDto) {
        return empresaService.salvar(empresaDto);
    }

    @PutMapping("/{idEmpresa}")
    public ResponseEntity<EmpresaDto> atualizar(@PathVariable Integer idEmpresa, @RequestBody EmpresaDto empresaDto) {
        return empresaService.atualizar(idEmpresa, empresaDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idEmpresa}")
    public ResponseEntity<Void> deletar(@PathVariable Integer idEmpresa) {
        return empresaService.deletar(idEmpresa)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
