package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.GradoAcademicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradoAcademicoRepository extends JpaRepository<GradoAcademicoEntity, Integer> {
    List<GradoAcademicoEntity> findAllByInstitucion_IdInstitucion(Integer idInstitucion);
    boolean existsByNombreGradoAndAnioAcademico_IdAnioAcademicoAndInstitucion_IdInstitucionAndNivelAcademico_IdNivelAcademicoAndTurno_IdTurno(
            String nombreGrado,
            Integer idAnioAcademico,
            Integer idInstitucion,
            Integer idNivelAcademico,
            Integer idTurno
    );
    boolean existsByNombreGradoAndAnioAcademico_IdAnioAcademicoAndInstitucion_IdInstitucionAndNivelAcademico_IdNivelAcademicoAndTurno_IdTurnoAndIdGradoAcademicoNot(
            String nombreGrado,
            Integer idAnioAcademico,
            Integer idInstitucion,
            Integer idNivelAcademico,
            Integer idTurno,
            Integer idGradoAcademico
    );


}
