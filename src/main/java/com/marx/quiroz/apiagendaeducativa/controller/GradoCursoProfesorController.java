package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.dto.request.GradoCursoProfesorCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.GradoCursoProfesorResponseDto;
import com.marx.quiroz.apiagendaeducativa.service.GradoCursoProfesorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profesor")
public class GradoCursoProfesorController {

    private final GradoCursoProfesorService service;

    public GradoCursoProfesorController(GradoCursoProfesorService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<GradoCursoProfesorResponseDto> asignarProfesor(
            @RequestBody GradoCursoProfesorCreateDto request) {
        return ResponseEntity.ok(service.asignarProfesor(request));
    }

    @GetMapping("/list/grado/{idGrado}")
    public ResponseEntity<List<GradoCursoProfesorResponseDto>> obtenerProfesoresPorGrado(
            @PathVariable Integer idGrado) {
        return ResponseEntity.ok(service.obtenerProfesoresPorGrado(idGrado));
    }
}
