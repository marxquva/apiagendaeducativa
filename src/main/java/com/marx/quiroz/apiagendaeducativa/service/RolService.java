package com.marx.quiroz.apiagendaeducativa.service;

import com.marx.quiroz.apiagendaeducativa.entity.RolEntity;

import java.util.List;

public interface RolService {
    RolEntity crearRol(RolEntity rol);
    List<RolEntity> obtenerRoles();
    RolEntity obtenerRolById(int id);
    RolEntity actualizarRol(Integer id, RolEntity rol);
}
