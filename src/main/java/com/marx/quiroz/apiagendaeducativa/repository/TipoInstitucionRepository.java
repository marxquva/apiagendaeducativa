package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.TipoInstitucionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoInstitucionRepository extends JpaRepository<TipoInstitucionEntity, Integer> {
    boolean existsByNombreTipoInstitucion(String nombre);
}
