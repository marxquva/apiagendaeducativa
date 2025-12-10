package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.dto.request.GradoAcademicoCreateDTO;
import com.marx.quiroz.apiagendaeducativa.dto.response.GradoAcademicoResponseDTO;
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
    public ResponseEntity<GradoAcademicoResponseDTO> crearGrado(@RequestBody GradoAcademicoCreateDTO gradoDto) {
        return ResponseEntity.ok(gradoService.crearGrado(gradoDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<GradoAcademicoResponseDTO> obtenerGrado(@PathVariable Integer id) {
        return ResponseEntity.ok(gradoService.obtenerGrado(id));
    }

    @GetMapping("/list/institucion/{idInstitucion}")
    public ResponseEntity<List<GradoAcademicoResponseDTO>> obtenerGradosPorInstitucion(
            @PathVariable Integer idInstitucion
    ) {
        return ResponseEntity.ok(gradoService.obtenerGradosPorInstitucion(idInstitucion));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GradoAcademicoResponseDTO> actualizarGrado(
            @PathVariable Integer id,
            @RequestBody GradoAcademicoCreateDTO gradoDto
    ) {
        return ResponseEntity.ok(gradoService.actualizarGrado(id, gradoDto));
    }
}
