package projeto.farmaciaSenai.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.farmaciaSenai.dto.UsuarioDto;
import projeto.farmaciaSenai.model.UsuarioModel;
import projeto.farmaciaSenai.service.UsuarioService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioModel> criar(@Valid @RequestBody UsuarioDto dto) {
        UsuarioModel usuario = usuarioService.salvar(dto);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarTudo(){
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioModel> listarPorId(@PathVariable Integer idUsuario){
        return usuarioService.buscarPorId(idUsuario).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
