package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MsCursoRepository extends JpaRepository<CursoEntity, Integer> {
    List<CursoEntity> findByInstitucionEntity_IdInstitucion(Integer idInstitucion);
    Optional<CursoEntity> findByIdCursoAndInstitucionEntity_IdInstitucion(Integer idCurso, Integer idInstitucion);
    boolean existsByNombreCursoAndInstitucionEntity_IdInstitucion(String nombreCurso, Integer idInstitucion);
    boolean existsByNombreCursoAndInstitucionEntity_IdInstitucionAndIdCursoNot(
            String nombreCurso,
            Integer idInstitucion,
            Integer idCurso
    );
}
