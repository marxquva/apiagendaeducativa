package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.entity.PeriodoEntity;

import java.util.List;

public interface PeriodoService {
    PeriodoEntity crearPeriodo(PeriodoEntity periodo);
    List<PeriodoEntity> obtenerPeriodos();
    PeriodoEntity obtenerPeriodoById(int id);
    PeriodoEntity actualizarPeriodo(Integer id, PeriodoEntity periodo);
}
