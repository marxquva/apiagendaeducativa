package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.dto.request.CursoCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.request.IndicadorCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.request.TurnoCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.CursoResponseDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.IndicadorResponseDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.TurnoResponseDto;
import com.marx.quiroz.apiagendaeducativa.service.MsCursoService;
import com.marx.quiroz.apiagendaeducativa.service.MsIndicadorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/indicador")
public class MsIndicadorController {

    private final MsIndicadorService indicadorService;
    public MsIndicadorController(MsIndicadorService indicadorService) {
        this.indicadorService = indicadorService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<IndicadorResponseDto>> obtenerIndicadores() {
        List<IndicadorResponseDto> indicadores = indicadorService.obtenerIndicadores();
        return ResponseEntity.ok(indicadores);
    }

    @GetMapping("/list/institucion/{idInstitucion}")
    public ResponseEntity<List<IndicadorResponseDto>> obtenerIndicadoresPorInstitucion(
            @PathVariable Integer idInstitucion) {

        return ResponseEntity.ok(indicadorService.obtenerIndicadoresPorInstitucion(idInstitucion));
    }

    @GetMapping("/get/{idIndicador}/institucion/{idInstitucion}")
    public ResponseEntity<IndicadorResponseDto> obtenerIndicadorPorIdInstitucion(
            @PathVariable Integer idInstitucion,
            @PathVariable Integer idIndicador) {

        return ResponseEntity.ok(
                indicadorService.obtenerIndicadorPorIdInstitucion(idIndicador, idInstitucion)
        );
    }

    @PostMapping("/save")
    public ResponseEntity<IndicadorResponseDto> crearIndicador(@Valid @RequestBody IndicadorCreateDto indicador) {
        IndicadorResponseDto nuevoIndicador = indicadorService.crearIndicador(indicador);
        return ResponseEntity.ok(nuevoIndicador);
    }

    @PutMapping("/update/{idIndicador}")
    public ResponseEntity<IndicadorResponseDto> actualizarIndicador(
            @PathVariable Integer idIndicador,
            @RequestBody IndicadorCreateDto indicador) {

        IndicadorResponseDto indicadorActualizado = indicadorService.actualizarIndicador(idIndicador, indicador);
        return ResponseEntity.ok(indicadorActualizado);
    }


}
