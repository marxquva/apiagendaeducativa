package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.dto.request.NivelCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.NivelResponseDto;
import com.marx.quiroz.apiagendaeducativa.service.MsNivelAcademicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nivelacademico")
public class MsNivelAcademicoController {

    private final MsNivelAcademicoService nivelService;
    public MsNivelAcademicoController(MsNivelAcademicoService nivelService) {
        this.nivelService = nivelService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<NivelResponseDto>> obtenerNiveles() {
        List<NivelResponseDto> niveles = nivelService.obtenerNiveles();
        return ResponseEntity.ok(niveles);
    }

    @GetMapping("/list/institucion/{idInstitucion}")
    public ResponseEntity<List<NivelResponseDto>> obtenerNivelesPorInstitucion(
            @PathVariable Integer idInstitucion) {

        return ResponseEntity.ok(nivelService.obtenerNivelesPorInstitucion(idInstitucion));
    }

    @GetMapping("/get/{idNivel}/institucion/{idInstitucion}")
    public ResponseEntity<NivelResponseDto> obtenerNivelPorIdInstitucion(
            @PathVariable Integer idInstitucion,
            @PathVariable Integer idNivel) {

        return ResponseEntity.ok(
                nivelService.obtenerNivelPorIdInstitucion(idNivel, idInstitucion)
        );
    }

    @PostMapping("/save")
    public ResponseEntity<NivelResponseDto> crearNivel(@Valid @RequestBody NivelCreateDto nivel) {
        NivelResponseDto nuevoNivel = nivelService.crearNivel(nivel);
        return ResponseEntity.ok(nuevoNivel);
    }

    @PutMapping("/update/{idNivel}")
    public ResponseEntity<NivelResponseDto> actualizarNivel(
            @PathVariable Integer idNivel,
            @RequestBody NivelCreateDto nivel) {

        NivelResponseDto nivelActualizado = nivelService.actualizarNivel(idNivel, nivel);
        return ResponseEntity.ok(nivelActualizado);
    }



}
