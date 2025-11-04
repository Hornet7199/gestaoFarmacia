// controller/FuncionarioController.java
package projeto.farmaciaSenai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.farmaciaSenai.dto.FuncionarioDto;
import projeto.farmaciaSenai.service.FuncionarioService;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public List<FuncionarioDto> listar() {
        return funcionarioService.listar();
    }

    @GetMapping("/{idFuncionario}")
    public ResponseEntity<FuncionarioDto> buscar(@PathVariable Integer idFuncionario) {
        return funcionarioService.buscar(idFuncionario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FuncionarioDto> criar(@RequestBody FuncionarioDto funcionarioDto) {
        return funcionarioService.salvar(funcionarioDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{idFuncionario}")
    public ResponseEntity<FuncionarioDto> atualizar(@PathVariable Integer idFuncionario, @RequestBody FuncionarioDto funcionarioDto) {
        return funcionarioService.atualizar(idFuncionario, funcionarioDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idFuncionario}")
    public ResponseEntity<Void> deletar(@PathVariable Integer idFuncionario) {
        return funcionarioService.deletar(idFuncionario)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
