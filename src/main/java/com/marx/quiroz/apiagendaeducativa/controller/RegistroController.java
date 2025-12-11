package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.dto.request.RegistroRequestDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.PersonaResponseDto;
import com.marx.quiroz.apiagendaeducativa.service.RegistroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/registro")
public class RegistroController {

    private final RegistroService registroService;

    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @PostMapping("/save")
    public ResponseEntity<PersonaResponseDto> registrar(@Valid @RequestBody RegistroRequestDto request) {
        PersonaResponseDto resultado = registroService.registrarPersonaUsuario(request);
        return ResponseEntity.ok(resultado);
    }
}
