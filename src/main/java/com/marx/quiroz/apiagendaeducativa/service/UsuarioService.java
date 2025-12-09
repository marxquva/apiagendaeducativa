package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.entity.PersonaEntity;
import com.marx.quiroz.apiagendaeducativa.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioService {
    UsuarioEntity crearUsuario(UsuarioEntity usuario);
    List<UsuarioEntity> obtenerUsuarios();
    UsuarioEntity obtenerUsuarioById(int id);
}
