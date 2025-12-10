package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.IndicadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MsIndicadorRepository extends JpaRepository<IndicadorEntity, Integer> {
    List<IndicadorEntity> findByInstitucionEntity_IdInstitucion(Integer idInstitucion);
    Optional<IndicadorEntity> findByIdIndicadorAndInstitucionEntity_IdInstitucion(Integer idIndicador, Integer idInstitucion);
    boolean existsByNombreIndicadorAndInstitucionEntity_IdInstitucion(String nombreIndicador, Integer idInstitucion);
    boolean existsByNombreIndicadorAndInstitucionEntity_IdInstitucionAndIdIndicadorNot(
            String nombreIndicador,
            Integer idInstitucion,
            Integer idIndicador
    );
}
