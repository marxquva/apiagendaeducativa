package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.entity.TipoInstitucionEntity;

import java.util.List;

public interface TipoInstitucionService {
    TipoInstitucionEntity crearTipoInstitucion(TipoInstitucionEntity tipoInstitucion);
    List<TipoInstitucionEntity> obtenerTiposInstitucion();
    TipoInstitucionEntity obtenerTipoInstitucionById(int id);
    TipoInstitucionEntity actualizarTipoInstitucion(Integer id, TipoInstitucionEntity tipoInstitucion);
}
