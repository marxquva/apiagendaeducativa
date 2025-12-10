package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.NivelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MsNivelAcademicoRepository extends JpaRepository<NivelEntity, Integer> {
    List<NivelEntity> findByInstitucionEntity_IdInstitucion(Integer idInstitucion);
    Optional<NivelEntity> findByIdNivelAcademicoAndInstitucionEntity_IdInstitucion(Integer idNivelAcademico, Integer idInstitucion);
    boolean existsByNombreNivelAcademicoAndInstitucionEntity_IdInstitucion(String nombreNivelAcademico, Integer idInstitucion);
    boolean existsByNombreNivelAcademicoAndInstitucionEntity_IdInstitucionAndIdNivelAcademicoNot(
            String nombreNivelAcademico,
            Integer idInstitucion,
            Integer idNivelAcademico
    );

}
