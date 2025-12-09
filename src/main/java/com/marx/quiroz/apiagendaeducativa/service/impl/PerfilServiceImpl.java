package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.entity.PerfilEntity;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.repository.PerfilRepository;
import com.marx.quiroz.apiagendaeducativa.service.PerfilService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilServiceImpl implements PerfilService {

    private final PerfilRepository perfilRepository;
    public PerfilServiceImpl(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    @Override
    public List<PerfilEntity> obtenerPerfiles() {
        return perfilRepository.findAll();
    }

    @Override
    public PerfilEntity obtenerPerfilById(int id) {
        return perfilRepository.findById(id).
                orElseThrow(() -> new InvalidOperationException("Perfil con ID " + id + " no se encuentra."));
    }

    @Override
    public PerfilEntity crearPerfil(PerfilEntity perfil) {
        if(perfilRepository.existsByNombre(perfil.getNombre())){
            throw new InvalidOperationException("El nombre del rol " + perfil.getNombre() + " ya está registrado");
        }

        PerfilEntity perfilEntity = new PerfilEntity();
        perfilEntity.setNombre(perfil.getNombre());
        perfilEntity.setDescripcion(perfil.getDescripcion());
        perfilRepository.save(perfilEntity);
        return perfilEntity;
    }

    @Override
    public PerfilEntity actualizarPerfil(Integer id, PerfilEntity perfil) {

        PerfilEntity perfilExistente = perfilRepository.findById(id)
                .orElseThrow(() -> new InvalidOperationException("Perfil con ID " + id + " no se encuentra."));

        if(perfilRepository.existsByNombre(perfil.getNombre())
                && !perfilExistente.getNombre().equals(perfil.getNombre())) {

            throw new InvalidOperationException("El nombre del perfil " + perfil.getNombre() + " ya está registrado");
        }

        perfilExistente.setNombre(perfil.getNombre());
        perfilExistente.setDescripcion(perfil.getDescripcion());
        perfilExistente.setCondicion(perfil.getCondicion());
        perfilExistente.setEstado(perfil.getEstado());

        return perfilRepository.save(perfilExistente);
    }

}
