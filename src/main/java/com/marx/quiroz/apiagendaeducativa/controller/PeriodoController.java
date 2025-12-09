package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.entity.PerfilEntity;
import com.marx.quiroz.apiagendaeducativa.entity.PeriodoEntity;
import com.marx.quiroz.apiagendaeducativa.service.PerfilService;
import com.marx.quiroz.apiagendaeducativa.service.PeriodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/periodo")
public class PeriodoController {
    private final PeriodoService periodoService;
    public PeriodoController(PeriodoService periodoService) {
        this.periodoService = periodoService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<PeriodoEntity>> obtenerPeriodos() {
        List<PeriodoEntity> periodos = periodoService.obtenerPeriodos();
        return ResponseEntity.ok(periodos);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PeriodoEntity> obtenerPeriodoById(@PathVariable int id) {
        PeriodoEntity periodo = periodoService.obtenerPeriodoById(id);
        return ResponseEntity.ok(periodo);
    }

    @PostMapping("/save")
    public ResponseEntity<PeriodoEntity> crearPeriodo(@Valid @RequestBody PeriodoEntity periodoEntity) {
        PeriodoEntity periodo = periodoService.crearPeriodo(periodoEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(periodo);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PeriodoEntity> actualizarPeriodo(
            @PathVariable Integer id,
            @Valid @RequestBody PeriodoEntity periodoEntity) {

        PeriodoEntity periodo = periodoService.actualizarPeriodo(id, periodoEntity);
        return ResponseEntity.ok(periodo);
    }

}
