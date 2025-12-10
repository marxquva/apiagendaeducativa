package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.UsuarioInstitucionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioInstitucionRepository extends JpaRepository<UsuarioInstitucionEntity, Integer> {
    boolean existsByUsuarioIdUsuarioAndInstitucionIdInstitucion(Integer idUsuario, Integer idInstitucion);
}
