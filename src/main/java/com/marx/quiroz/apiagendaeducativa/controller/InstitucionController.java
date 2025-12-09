package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.dto.request.InstitucionCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.InstitucionResponseDto;
import com.marx.quiroz.apiagendaeducativa.entity.InstitucionEntity;
import com.marx.quiroz.apiagendaeducativa.entity.PeriodoEntity;
import com.marx.quiroz.apiagendaeducativa.service.InstitucionService;
import com.marx.quiroz.apiagendaeducativa.service.PeriodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/institucion")
public class InstitucionController {

    private final InstitucionService institucionService;
    public InstitucionController(InstitucionService institucionService) {
        this.institucionService = institucionService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<InstitucionResponseDto>> obtenerInstituciones() {
        List<InstitucionResponseDto> instituciones = institucionService.obtenerInstituciones();
        return ResponseEntity.ok(instituciones);
    }

    @GetMapping("/list/tipo/{idTipo}")
    public ResponseEntity<List<InstitucionResponseDto>> obtenerInstitucionesPorTipo(@PathVariable Integer idTipo) {
        List<InstitucionResponseDto> instituciones = institucionService.obtenerInstitucionesPorTipo(idTipo);
        return ResponseEntity.ok(instituciones);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<InstitucionResponseDto> obtenerInstitucionById(@PathVariable int id) {
        InstitucionResponseDto institucion = institucionService.obtenerInstitucionById(id);
        return ResponseEntity.ok(institucion);
    }

    @PostMapping("/save")
    public ResponseEntity<InstitucionResponseDto> crearInstitucion(@Valid @RequestBody InstitucionCreateDto institucionDto) {
        InstitucionResponseDto institucion = institucionService.crearInstitucion(institucionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(institucion);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InstitucionResponseDto> actualizarPeriodo(
            @PathVariable Integer id,
            @Valid @RequestBody InstitucionCreateDto institucionDto) {

        InstitucionResponseDto institucion = institucionService.actualizarInstitucion(id, institucionDto);
        return ResponseEntity.ok(institucion);
    }

    @PatchMapping("/update/{id}/estado")
    public ResponseEntity<InstitucionResponseDto> cambiarEstado(
            @PathVariable Integer id,
            @RequestParam Integer estado) {

        InstitucionResponseDto respuesta = institucionService.cambiarEstado(id, estado);
        return ResponseEntity.ok(respuesta);
    }

}
