package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.dto.request.GradoAcademicoCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.GradoAcademicoResponseDto;
import com.marx.quiroz.apiagendaeducativa.service.GradoAcademicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gradoacademico")
public class GradoAcademicoController {
    private final GradoAcademicoService gradoService;

    public GradoAcademicoController(GradoAcademicoService gradoService) {
        this.gradoService = gradoService;
    }

    @PostMapping("/save")
    public ResponseEntity<GradoAcademicoResponseDto> crearGrado(@RequestBody GradoAcademicoCreateDto gradoDto) {
        return ResponseEntity.ok(gradoService.crearGrado(gradoDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<GradoAcademicoResponseDto> obtenerGrado(@PathVariable Integer id) {
        return ResponseEntity.ok(gradoService.obtenerGrado(id));
    }

    @GetMapping("/list/institucion/{idInstitucion}")
    public ResponseEntity<List<GradoAcademicoResponseDto>> obtenerGradosPorInstitucion(
            @PathVariable Integer idInstitucion
    ) {
        return ResponseEntity.ok(gradoService.obtenerGradosPorInstitucion(idInstitucion));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GradoAcademicoResponseDto> actualizarGrado(
            @PathVariable Integer id,
            @RequestBody GradoAcademicoCreateDto gradoDto
    ) {
        return ResponseEntity.ok(gradoService.actualizarGrado(id, gradoDto));
    }
}
