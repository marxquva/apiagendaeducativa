package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.entity.RolEntity;
import com.marx.quiroz.apiagendaeducativa.service.RolService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rolacademico")
public class RolController {
    private final RolService rolService;
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<RolEntity>> obtenerRoles() {
        List<RolEntity> roles = rolService.obtenerRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RolEntity> obtenerRolPorId(@PathVariable int id) {
        RolEntity rolAcademico = rolService.obtenerRolById(id);
        return ResponseEntity.ok(rolAcademico);
    }


    @PostMapping("/save")
    public ResponseEntity<RolEntity> crearEstudiante(@Valid @RequestBody RolEntity rolEntity) {
        RolEntity rolAcademico = rolService.crearRol(rolEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolAcademico);
    }
}
