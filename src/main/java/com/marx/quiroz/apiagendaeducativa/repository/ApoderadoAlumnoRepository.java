package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.ApoderadoAlumnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ApoderadoAlumnoRepository extends JpaRepository<ApoderadoAlumnoEntity, Integer> {
    List<ApoderadoAlumnoEntity> findByAlumno_IdPersona(Integer idAlumno);
    boolean existsByAlumno_IdPersonaAndEsPrincipal(Integer idAlumno, Integer esPrincipal);

}
