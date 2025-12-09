package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.entity.PerfilEntity;
import com.marx.quiroz.apiagendaeducativa.entity.RolEntity;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.repository.RolRepository;
import com.marx.quiroz.apiagendaeducativa.service.RolService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {
    private final RolRepository rolRepository;
    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public RolEntity crearRol(RolEntity rol) {
        if(rolRepository.existsByNombre(rol.getNombre())){
            throw new InvalidOperationException("El nombre del rol " + rol.getNombre() + " ya está registrado");
        }

        RolEntity rolEntity = new RolEntity();
        rolEntity.setNombre(rol.getNombre());
        rolEntity.setDescripcion(rol.getDescripcion());
        rolEntity.setNivel(rol.getNivel());
        rolRepository.save(rolEntity);
        return rolEntity;
    }

    @Override
    public List<RolEntity> obtenerRoles() {
        return rolRepository.findAll();
    }

    @Override
    public RolEntity obtenerRolById(int id) {
        return rolRepository.findById(id).
                orElseThrow(() -> new InvalidOperationException("Rol con ID " + id + " no se encuentra."));
    }

    @Override
    public RolEntity actualizarRol(Integer id, RolEntity rol) {

        RolEntity rolExistente = rolRepository.findById(id)
                .orElseThrow(() -> new InvalidOperationException("Perfil con ID " + id + " no se encuentra."));

        if(rolRepository.existsByNombre(rol.getNombre())
                && !rolExistente.getNombre().equals(rol.getNombre())) {

            throw new InvalidOperationException("El nombre del perfil " + rol.getNombre() + " ya está registrado");
        }

        rolExistente.setNombre(rol.getNombre());
        rolExistente.setDescripcion(rol.getDescripcion());
        rolExistente.setNivel(rol.getNivel());
        rolExistente.setEstado(rol.getEstado());

        return rolRepository.save(rolExistente);
    }
}
