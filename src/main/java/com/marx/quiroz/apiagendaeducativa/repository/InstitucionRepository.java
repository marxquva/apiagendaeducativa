package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.InstitucionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitucionRepository extends JpaRepository<InstitucionEntity, Integer> {
    boolean existsByNombreInstitucion(String nombre);
    List<InstitucionEntity> findByTipoInstitucion_IdTipoInstitucion(Integer idTipo);
}
