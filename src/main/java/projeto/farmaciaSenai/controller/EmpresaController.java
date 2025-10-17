package projeto.farmaciaSenai.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.farmaciaSenai.dto.EmpresaDto;
import projeto.farmaciaSenai.model.EmpresaModel;
import projeto.farmaciaSenai.service.EmpresaService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService){
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<EmpresaModel> criar(@Valid @RequestBody EmpresaDto dto){
        EmpresaModel empresaModel = empresaService.salvar(dto);
        return ResponseEntity.ok(empresaModel);
    }

    @GetMapping
    public ResponseEntity<List<EmpresaModel>> listarTodas(){
        return ResponseEntity.ok(empresaService.listarEmpresas());
    }

    @GetMapping("/{idEmpresa}")
    public ResponseEntity<EmpresaModel> listarPorIdEmpresa(@PathVariable Integer idEmpresa){
        return empresaService.buscarPorIdEmpresa(idEmpresa).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
