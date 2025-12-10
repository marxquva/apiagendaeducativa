package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {
    Optional<PersonaEntity> findByNumeroDocumento(String numeroDocumento);
    @Query("SELECT p FROM PersonaEntity p JOIN p.personaInstituciones pi " +
            "WHERE p.numeroDocumento = :numeroDocumento AND pi.institucion.idInstitucion = :idInstitucion")
    Optional<PersonaEntity> findByNumeroDocumentoAndInstitucion(String numeroDocumento, Integer idInstitucion);

    @Query("SELECT DISTINCT p FROM PersonaEntity p " +
            "JOIN p.personaInstituciones pi " +
            "WHERE pi.institucion.idInstitucion = :idInstitucion")
    List<PersonaEntity> findAllByInstitucion(@Param("idInstitucion") Integer idInstitucion);

    @Query("SELECT p FROM PersonaEntity p " +
            "JOIN p.personaInstituciones pi " +
            "WHERE p.idPersona = :idPersona " +
            "AND pi.institucion.idInstitucion = :idInstitucion")
    Optional<PersonaEntity> findByIdAndInstitucion(
            @Param("idPersona") Integer idPersona,
            @Param("idInstitucion") Integer idInstitucion);

}
