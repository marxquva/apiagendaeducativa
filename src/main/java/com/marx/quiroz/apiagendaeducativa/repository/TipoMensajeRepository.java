package com.marx.quiroz.apiagendaeducativa.repository;

import com.marx.quiroz.apiagendaeducativa.entity.TipoMensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMensajeRepository extends JpaRepository<TipoMensajeEntity, Integer> {
    boolean existsByNombreTipoMensaje(String nombre);
}
