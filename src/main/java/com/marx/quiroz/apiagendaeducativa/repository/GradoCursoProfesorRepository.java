package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.GradoCursoProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GradoCursoProfesorRepository extends JpaRepository<GradoCursoProfesorEntity, Integer> {
    List<GradoCursoProfesorEntity> findByGradoAcademico_IdGradoAcademico(Integer idGrado);
}
