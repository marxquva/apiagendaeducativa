package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.entity.TipoDocumentoEntity;
import com.marx.quiroz.apiagendaeducativa.entity.TipoInstitucionEntity;
import com.marx.quiroz.apiagendaeducativa.service.TipoDocumentoService;
import com.marx.quiroz.apiagendaeducativa.service.TipoInstitucionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipoinstitucion")
public class TipoInstitucionController {

    private final TipoInstitucionService tipoInstitucionService;
    public TipoInstitucionController(TipoInstitucionService tipoInstitucionService) {
        this.tipoInstitucionService = tipoInstitucionService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<TipoInstitucionEntity>> obtenerTiposInstitucion() {
        List<TipoInstitucionEntity> tipoInstitucion = tipoInstitucionService.obtenerTiposInstitucion();
        return ResponseEntity.ok(tipoInstitucion);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TipoInstitucionEntity> obtenerTipoInstitucionById(@PathVariable int id) {
        TipoInstitucionEntity tipoInstitucion = tipoInstitucionService.obtenerTipoInstitucionById(id);
        return ResponseEntity.ok(tipoInstitucion);
    }

    @PostMapping("/save")
    public ResponseEntity<TipoInstitucionEntity> crearTipoInstitucion(@Valid @RequestBody TipoInstitucionEntity tipoInstitucionEntity) {
        TipoInstitucionEntity tipoInstitucion = tipoInstitucionService.crearTipoInstitucion(tipoInstitucionEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoInstitucion);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TipoInstitucionEntity> actualizarTipoInstitucion(
            @PathVariable Integer id,
            @Valid @RequestBody TipoInstitucionEntity tipoInstitucionEntity) {

        TipoInstitucionEntity tipoInstitucion = tipoInstitucionService.actualizarTipoInstitucion(id, tipoInstitucionEntity);
        return ResponseEntity.ok(tipoInstitucion);
    }
}
