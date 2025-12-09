package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.entity.TipoDocumentoEntity;
import com.marx.quiroz.apiagendaeducativa.service.TipoDocumentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipodocumento")
public class TipoDocumentoController {

    private final TipoDocumentoService tipoDocumentoService;
    public TipoDocumentoController(TipoDocumentoService tipoDocumentoService) {
        this.tipoDocumentoService = tipoDocumentoService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<TipoDocumentoEntity>> obtenerTiposDocumento() {
        List<TipoDocumentoEntity> tiposDocumento = tipoDocumentoService.obtenerTiposDocumento();
        return ResponseEntity.ok(tiposDocumento);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TipoDocumentoEntity> obtenerTipoDocumentoById(@PathVariable int id) {
        TipoDocumentoEntity tipoDocumento = tipoDocumentoService.obtenerTipoDocumentoById(id);
        return ResponseEntity.ok(tipoDocumento);
    }

    @PostMapping("/save")
    public ResponseEntity<TipoDocumentoEntity> crearTipoDocumento(@Valid @RequestBody TipoDocumentoEntity tipoDocumentoEntity) {
        TipoDocumentoEntity tipoDocumento = tipoDocumentoService.crearTipoDocumento(tipoDocumentoEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoDocumento);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TipoDocumentoEntity> actualizarTipoDocumento(
            @PathVariable Integer id,
            @Valid @RequestBody TipoDocumentoEntity tipoDocumentoEntity) {

        TipoDocumentoEntity tipoDocumento = tipoDocumentoService.actualizarTipoDocumento(id, tipoDocumentoEntity);
        return ResponseEntity.ok(tipoDocumento);
    }
}
