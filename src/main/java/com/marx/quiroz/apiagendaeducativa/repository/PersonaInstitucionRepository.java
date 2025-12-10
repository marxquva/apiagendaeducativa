package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.PersonaInstitucionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaInstitucionRepository extends JpaRepository<PersonaInstitucionEntity, Integer> {
    boolean existsByPersonaIdPersonaAndInstitucionIdInstitucion(Integer idPersona, Integer idInstitucion);
}
