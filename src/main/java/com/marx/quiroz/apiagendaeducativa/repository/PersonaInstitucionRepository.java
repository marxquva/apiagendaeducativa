package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.PersonaInstitucionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaInstitucionRepository extends JpaRepository<PersonaInstitucionEntity, Integer> {
    boolean existsByPersonaIdPersonaAndInstitucionIdInstitucion(Integer idPersona, Integer idInstitucion);
    boolean existsByPersonaIdPersonaAndInstitucionIdInstitucionAndRolAcademicoIdRolAcademico(
            Integer idPersona,
            Integer idInstitucion,
            Integer idRolAcademico
    );
    List<PersonaInstitucionEntity>findByPersona_IdPersona(Integer idPersona);
    List<PersonaInstitucionEntity> findByPersona_IdPersonaAndInstitucion_IdInstitucion(Integer idPersona, Integer idInstitucion);


}
