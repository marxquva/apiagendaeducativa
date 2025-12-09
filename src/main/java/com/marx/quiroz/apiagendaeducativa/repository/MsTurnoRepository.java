package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.TurnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MsTurnoRepository extends JpaRepository<TurnoEntity, Integer> {
    List<TurnoEntity> findByInstitucionEntity_IdInstitucion(Integer idInstitucion);
    Optional<TurnoEntity> findByIdTurnoAndInstitucionEntity_IdInstitucion(Integer idTurno, Integer idInstitucion);
    boolean existsByNombreTurnoAndInstitucionEntity_IdInstitucion(String nombreTurno, Integer idInstitucion);
    boolean existsByNombreTurnoAndInstitucionEntity_IdInstitucionAndIdTurnoNot(
            String nombreTurno,
            Integer idInstitucion,
            Integer idTurno
    );

}
