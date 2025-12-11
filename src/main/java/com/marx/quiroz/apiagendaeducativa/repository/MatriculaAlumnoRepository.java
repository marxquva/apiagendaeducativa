package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.MatriculaAlumnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaAlumnoRepository extends JpaRepository<MatriculaAlumnoEntity, Integer> {
    boolean existsByPersona_IdPersonaAndGradoAcademico_IdGradoAcademico(Integer idPersona, Integer idGrado);
}
