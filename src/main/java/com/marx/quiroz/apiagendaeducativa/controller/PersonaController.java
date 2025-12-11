package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.dto.request.PersonaInstitucionAddRequestDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.ApiResponseDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.PersonaInstitucionAddResponseDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.PersonaResponseDto;
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
    public ResponseEntity<List<PersonaResponseDto>> obtenerPersonas() {
        return ResponseEntity.ok(personaService.obtenerPersonas());
    }

    @GetMapping("/list/paginate")
    public ResponseEntity<Page<PersonaResponseDto>> obtenerPersonasPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(personaService.obtenerPersonasPaginado(page, size));
    }

    @GetMapping("/list/institucion/{idInstitucion}/personas")
    public ResponseEntity<List<PersonaResponseDto>> obtenerPersonasPorInstitucion(
            @PathVariable Integer idInstitucion) {

        return ResponseEntity.ok(personaService.obtenerPersonasPorInstitucion(idInstitucion));
    }

    @GetMapping("/get/institucion/{idInstitucion}/persona/{idPersona}")
    public ResponseEntity<PersonaResponseDto> obtenerPersonaPorInstitucion(
            @PathVariable Integer idInstitucion,
            @PathVariable Integer idPersona) {

        return ResponseEntity.ok(personaService.obtenerPersonaPorInstitucion(idInstitucion, idPersona));
    }

    @PostMapping("/save/persona/institucion")
    public ResponseEntity<PersonaInstitucionAddResponseDto> agregarPersonaInstitucion(@RequestBody @Valid PersonaInstitucionAddRequestDto dto) {
        PersonaInstitucionAddResponseDto persona = personaService.agregarPersonaAInstitucion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(persona);
    }

    @PutMapping("/update/persona/institucion/{idPersonaInstitucion}")
    public ResponseEntity<PersonaInstitucionAddResponseDto> actualizarPersonaInstitucion(
            @PathVariable Integer idPersonaInstitucion,
            @Valid @RequestBody PersonaInstitucionAddRequestDto dto) {

        return ResponseEntity.ok(
                personaService.actualizarPersonaInstitucion(idPersonaInstitucion, dto)
        );
    }

    @DeleteMapping("/delete/persona/institucion/{idPersonaInstitucion}")
    public ResponseEntity<ApiResponseDto> eliminarPersonaInstitucion(
            @PathVariable Integer idPersonaInstitucion) {

        personaService.eliminarPersonaInstitucion(idPersonaInstitucion);

        ApiResponseDto response = new ApiResponseDto("Se ha eliminado correctamente", 200);
        return ResponseEntity.ok(response);
    }




    @GetMapping("/search/documento/{numeroDocumento}")
    public ResponseEntity<PersonaResponseDto> obtenerPersonaPorNumeroDocumento(
            @PathVariable String numeroDocumento) {

        return ResponseEntity.ok(
                personaService.obtenerPersonaPorNumeroDocumento(numeroDocumento)
        );
    }

    @GetMapping("/search/institucion/{idInstitucion}/documento/{numeroDocumento}")
    public ResponseEntity<PersonaResponseDto> obtenerPersonaPorDocumentoEInstitucion(
            @PathVariable String numeroDocumento,
            @PathVariable Integer idInstitucion) {

        return ResponseEntity.ok(
                personaService.obtenerPersonaPorDocumentoEInstitucion(numeroDocumento, idInstitucion)
        );
    }
}
