package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MsCursoRepository extends JpaRepository<CursoEntity, Integer> {
}
