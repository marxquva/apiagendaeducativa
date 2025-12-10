package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.dto.request.CursoCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.CursoResponseDto;
import com.marx.quiroz.apiagendaeducativa.service.MsCursoService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/curso")
public class MsCursoController {

    private final MsCursoService cursoService;
    public MsCursoController(MsCursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CursoResponseDto>> obtenerCursos() {
        List<CursoResponseDto> cursos = cursoService.obtenerCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/list/institucion/{idInstitucion}")
    public ResponseEntity<List<CursoResponseDto>> obtenerCursosPorInstitucion(
            @PathVariable Integer idInstitucion) {

        return ResponseEntity.ok(cursoService.obtenerCursosPorInstitucion(idInstitucion));
    }

    @GetMapping("/get/{idCurso}/institucion/{idInstitucion}")
    public ResponseEntity<CursoResponseDto> obtenerCursoPorIdInstitucion(
            @PathVariable Integer idInstitucion,
            @PathVariable Integer idCurso) {

        return ResponseEntity.ok(
                cursoService.obtenerCursoPorIdInstitucion(idCurso, idInstitucion)
        );
    }

    @PostMapping("/save")
    public ResponseEntity<CursoResponseDto> crearCurso(@Valid @RequestBody CursoCreateDto curso) {
        CursoResponseDto nuevoCurso = cursoService.crearCurso(curso);
        return ResponseEntity.ok(nuevoCurso);
    }

    @PutMapping("/update/{idCurso}")
    public ResponseEntity<CursoResponseDto> actualizarCurso(
            @PathVariable Integer idCurso,
            @RequestBody CursoCreateDto curso) {

        CursoResponseDto cursoActualizado = cursoService.actualizarCurso(idCurso, curso);
        return ResponseEntity.ok(cursoActualizado);
    }
}
