package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.dto.request.PersonaInstitucionAddRequestDTO;
import com.marx.quiroz.apiagendaeducativa.dto.response.PersonaInstitucionAddResponseDTO;
import com.marx.quiroz.apiagendaeducativa.dto.response.PersonaResponseDTO;
import com.marx.quiroz.apiagendaeducativa.entity.PeriodoEntity;
import com.marx.quiroz.apiagendaeducativa.service.PersonaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persona")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<PersonaResponseDTO>> obtenerPersonas() {
        return ResponseEntity.ok(personaService.obtenerPersonas());
    }

    @GetMapping("/list/paginate")
    public ResponseEntity<Page<PersonaResponseDTO>> obtenerPersonasPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(personaService.obtenerPersonasPaginado(page, size));
    }

    @GetMapping("/list/institucion/{idInstitucion}/personas")
    public ResponseEntity<List<PersonaResponseDTO>> obtenerPersonasPorInstitucion(
            @PathVariable Integer idInstitucion) {

        return ResponseEntity.ok(personaService.obtenerPersonasPorInstitucion(idInstitucion));
    }

    @GetMapping("/get/institucion/{idInstitucion}/persona/{idPersona}")
    public ResponseEntity<PersonaResponseDTO> obtenerPersonaPorInstitucion(
            @PathVariable Integer idInstitucion,
            @PathVariable Integer idPersona) {

        return ResponseEntity.ok(personaService.obtenerPersonaPorInstitucion(idInstitucion, idPersona));
    }

    @PostMapping("/save/persona/institucion")
    public ResponseEntity<PersonaInstitucionAddResponseDTO> agregarPersonaInstitucion(@RequestBody @Valid PersonaInstitucionAddRequestDTO dto) {
        PersonaInstitucionAddResponseDTO persona = personaService.agregarPersonaAInstitucion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(persona);
    }

    @GetMapping("/search/documento/{numeroDocumento}")
    public ResponseEntity<PersonaResponseDTO> obtenerPersonaPorNumeroDocumento(
            @PathVariable String numeroDocumento) {

        return ResponseEntity.ok(
                personaService.obtenerPersonaPorNumeroDocumento(numeroDocumento)
        );
    }

    @GetMapping("/search/institucion/{idInstitucion}/documento/{numeroDocumento}")
    public ResponseEntity<PersonaResponseDTO> obtenerPersonaPorDocumentoEInstitucion(
            @PathVariable String numeroDocumento,
            @PathVariable Integer idInstitucion) {

        return ResponseEntity.ok(
                personaService.obtenerPersonaPorDocumentoEInstitucion(numeroDocumento, idInstitucion)
        );
    }
}
