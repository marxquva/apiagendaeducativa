package com.marx.quiroz.apiagendaeducativa.controller;

import com.marx.quiroz.apiagendaeducativa.entity.PerfilEntity;
import com.marx.quiroz.apiagendaeducativa.entity.RolEntity;
import com.marx.quiroz.apiagendaeducativa.entity.TipoMensajeEntity;
import com.marx.quiroz.apiagendaeducativa.service.TipoMensajeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipomensaje")
public class TipoMensajeController {

    private final TipoMensajeService tipoMensajeService;
    public TipoMensajeController(TipoMensajeService tipoMensajeService) {
        this.tipoMensajeService = tipoMensajeService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<TipoMensajeEntity>> obtenerTiposMensaje() {
        List<TipoMensajeEntity> tiposMensaje = tipoMensajeService.obtenerTiposMensaje();
        return ResponseEntity.ok(tiposMensaje);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TipoMensajeEntity> obtenerTipoMensajeById(@PathVariable int id) {
        TipoMensajeEntity tipoMensaje = tipoMensajeService.obtenerTipoMensajeById(id);
        return ResponseEntity.ok(tipoMensaje);
    }

    @PostMapping("/save")
    public ResponseEntity<TipoMensajeEntity> crearTipoMensaje(@Valid @RequestBody TipoMensajeEntity tipoMensajeEntity) {
        TipoMensajeEntity tipoMensaje = tipoMensajeService.crearTipoMensaje(tipoMensajeEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoMensaje);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TipoMensajeEntity> actualizarTipoMensaje(
            @PathVariable Integer id,
            @Valid @RequestBody TipoMensajeEntity tipoMensajeEntity) {

        TipoMensajeEntity tipoMensaje = tipoMensajeService.actualizarTipoMensaje(id, tipoMensajeEntity);
        return ResponseEntity.ok(tipoMensaje);
    }
}
