package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.entity.PerfilEntity;
import com.marx.quiroz.apiagendaeducativa.entity.PeriodoEntity;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.repository.PeriodoRepository;
import com.marx.quiroz.apiagendaeducativa.service.PeriodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodoServiceImpl implements PeriodoService {
    private final PeriodoRepository periodoRepository;
    public PeriodoServiceImpl(PeriodoRepository periodoRepository) {
        this.periodoRepository = periodoRepository;
    }

    @Override
    public List<PeriodoEntity> obtenerPeriodos() {
        return periodoRepository.findAll();
    }

    @Override
    public PeriodoEntity obtenerPeriodoById(int id) {
        return periodoRepository.findById(id).
                orElseThrow(() -> new InvalidOperationException("Periodo academico con ID " + id + " no se encuentra."));
    }

    @Override
    public PeriodoEntity crearPeriodo(PeriodoEntity periodo) {
        int codigo = periodo.getCodigo();
        if(periodoRepository.existsByCodigo(codigo)){
            throw new InvalidOperationException("El periodo academico " + codigo + " ya está registrado");
        }

        PeriodoEntity periodoEntity = new PeriodoEntity();
        periodoEntity.setCodigo(periodo.getCodigo());
        periodoEntity.setNombre(periodo.getNombre());
        periodoEntity.setDescripcion(periodo.getDescripcion());
        periodoEntity.setFechaInicio(periodo.getFechaInicio());
        periodoEntity.setFechaFinal(periodo.getFechaFinal());
        periodoRepository.save(periodoEntity);
        return periodoEntity;
    }

    @Override
    public PeriodoEntity actualizarPeriodo(Integer id, PeriodoEntity periodo) {

        PeriodoEntity periodoExistente = periodoRepository.findById(id)
                .orElseThrow(() -> new InvalidOperationException("Periodo con ID " + id + " no se encuentra."));

        if (periodoRepository.existsByCodigo(periodo.getCodigo())
                && !periodoExistente.getCodigo().equals(periodo.getCodigo())) {

            throw new InvalidOperationException("El código " + periodo.getCodigo() + " ya está registrado");
        }

        periodoExistente.setNombre(periodo.getNombre());
        periodoExistente.setCodigo(periodo.getCodigo());
        periodoExistente.setDescripcion(periodo.getDescripcion());
        periodoExistente.setFechaInicio(periodo.getFechaInicio());
        periodoExistente.setFechaFinal(periodo.getFechaFinal());
        periodoExistente.setEstado(periodo.getEstado());

        return periodoRepository.save(periodoExistente);
    }
}
