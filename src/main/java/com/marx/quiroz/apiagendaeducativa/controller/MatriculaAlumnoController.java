package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.dto.request.MatriculaAlumnoAddRequestDTO;
import com.marx.quiroz.apiagendaeducativa.dto.response.MatriculaAlumnoAddResponseDTO;
import com.marx.quiroz.apiagendaeducativa.service.MatriculaAlumnoService;
import com.marx.quiroz.apiagendaeducativa.service.MsCursoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/matricula")
public class MatriculaAlumnoController {

    private final MatriculaAlumnoService matriculaAlumnoService;

    public MatriculaAlumnoController(MatriculaAlumnoService matriculaAlumnoService) {
        this.matriculaAlumnoService = matriculaAlumnoService;
    }

    @PostMapping("/save")
    public ResponseEntity<MatriculaAlumnoAddResponseDTO> matricularAlumno(
            @RequestBody @Valid MatriculaAlumnoAddRequestDTO dto
    ) {
        return ResponseEntity.ok(matriculaAlumnoService.matricularAlumno(dto));
    }

}
