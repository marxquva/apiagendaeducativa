package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.dto.request.TurnoCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.TurnoResponseDto;
import com.marx.quiroz.apiagendaeducativa.entity.PerfilEntity;
import com.marx.quiroz.apiagendaeducativa.entity.TurnoEntity;
import com.marx.quiroz.apiagendaeducativa.service.MsTurnoService;
import com.marx.quiroz.apiagendaeducativa.service.PerfilService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/turno")
public class MsTurnoController {

    private final MsTurnoService turnoService;
    public MsTurnoController(MsTurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<TurnoResponseDto>> obtenerPerfiles() {
        List<TurnoResponseDto> turnos = turnoService.obtenerTurnos();
        return ResponseEntity.ok(turnos);
    }

    @GetMapping("/list/institucion/{idInstitucion}")
    public ResponseEntity<List<TurnoResponseDto>> obtenerTurnosPorInstitucion(
            @PathVariable Integer idInstitucion) {

        return ResponseEntity.ok(turnoService.obtenerTurnosPorInstitucion(idInstitucion));
    }

    @GetMapping("/get/{idTurno}/institucion/{idInstitucion}")
    public ResponseEntity<TurnoResponseDto> obtenerTurnoPorIdEInstitucion(
            @PathVariable Integer idInstitucion,
            @PathVariable Integer idTurno) {

        return ResponseEntity.ok(
                turnoService.obtenerTurnoPorIdInstitucion(idTurno, idInstitucion)
        );
    }

    @PostMapping("/save")
    public ResponseEntity<TurnoResponseDto> crearTurno(@Valid @RequestBody TurnoCreateDto turno) {
        TurnoResponseDto nuevoTurno = turnoService.crearTurno(turno);
        return ResponseEntity.ok(nuevoTurno);
    }

    @PutMapping("/update/{idTurno}")
    public ResponseEntity<TurnoResponseDto> actualizarTurno(
            @PathVariable Integer idTurno,
            @RequestBody TurnoCreateDto turno) {

        TurnoResponseDto turnoActualizado = turnoService.actualizarTurno(idTurno, turno);
        return ResponseEntity.ok(turnoActualizado);
    }

}
