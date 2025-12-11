package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.dto.request.ApoderadoAlumnoCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.ApoderadoAlumnoResponseDto;
import com.marx.quiroz.apiagendaeducativa.service.ApoderadoAlumnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apoderado")
public class ApoderadoAlumnoController {

    private final ApoderadoAlumnoService service;

    public ApoderadoAlumnoController(ApoderadoAlumnoService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<ApoderadoAlumnoResponseDto> asignarApoderado(
            @RequestBody ApoderadoAlumnoCreateDto request) {
        return ResponseEntity.ok(service.asignarApoderado(request));
    }

    @GetMapping("/list/alumno/{idAlumno}")
    public ResponseEntity<List<ApoderadoAlumnoResponseDto>> obtenerApoderadosPorAlumno(
            @PathVariable Integer idAlumno) {
        return ResponseEntity.ok(service.obtenerApoderadosPorAlumno(idAlumno));
    }
}
