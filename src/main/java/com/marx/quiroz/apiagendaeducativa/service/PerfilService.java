package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.entity.PerfilEntity;

import java.util.List;

public interface PerfilService {
    PerfilEntity crearPerfil(PerfilEntity perfil);
    List<PerfilEntity> obtenerPerfiles();
    PerfilEntity obtenerPerfilById(int id);
    PerfilEntity actualizarPerfil(Integer id, PerfilEntity perfil);
}
