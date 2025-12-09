package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.entity.PerfilEntity;
import com.marx.quiroz.apiagendaeducativa.service.PerfilService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/perfil")
public class PerfilController {

    private final PerfilService perfilService;
    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<PerfilEntity>> obtenerPerfiles() {
        List<PerfilEntity> perfiles = perfilService.obtenerPerfiles();
        return ResponseEntity.ok(perfiles);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PerfilEntity> obtenerPerfilById(@PathVariable int id) {
        PerfilEntity perfil = perfilService.obtenerPerfilById(id);
        return ResponseEntity.ok(perfil);
    }

    @PostMapping("/save")
    public ResponseEntity<PerfilEntity> crearPerfil(@Valid @RequestBody PerfilEntity perfilEntity) {
        PerfilEntity perfil = perfilService.crearPerfil(perfilEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(perfil);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PerfilEntity> actualizarPerfil(
            @PathVariable Integer id,
            @Valid @RequestBody PerfilEntity perfilEntity) {

        PerfilEntity perfil = perfilService.actualizarPerfil(id, perfilEntity);
        return ResponseEntity.ok(perfil);
    }

}
