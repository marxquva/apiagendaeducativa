package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.entity.UsuarioEntity;
import com.marx.quiroz.apiagendaeducativa.repository.UsuarioRepository;
import com.marx.quiroz.apiagendaeducativa.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioEntity crearUsuario(UsuarioEntity usuario) {
        return null;
    }

    @Override
    public List<UsuarioEntity> obtenerUsuarios() {
        return List.of();
    }

    @Override
    public UsuarioEntity obtenerUsuarioById(int id) {
        return null;
    }
}
