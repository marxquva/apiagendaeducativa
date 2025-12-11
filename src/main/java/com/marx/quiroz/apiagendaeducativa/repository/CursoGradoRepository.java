package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.CursoGradoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoGradoRepository extends JpaRepository<CursoGradoEntity,Integer> {
    boolean existsByGradoAcademico_IdGradoAcademicoAndCurso_IdCurso(Integer idGrado, Integer idCurso);
}
