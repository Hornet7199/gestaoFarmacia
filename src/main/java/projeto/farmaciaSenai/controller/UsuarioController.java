// controller/UsuarioController.java
package projeto.farmaciaSenai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.farmaciaSenai.dto.UsuarioDto;
import projeto.farmaciaSenai.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioDto> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDto> buscar(@PathVariable Integer idUsuario) {
        return usuarioService.buscar(idUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UsuarioDto criar(@RequestBody UsuarioDto usuarioDto) {
        return usuarioService.salvar(usuarioDto);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDto> atualizar(@PathVariable Integer idUsuario, @RequestBody UsuarioDto usuarioDto) {
        return usuarioService.atualizar(idUsuario, usuarioDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deletar(@PathVariable Integer idUsuario) {
        return usuarioService.deletar(idUsuario)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
