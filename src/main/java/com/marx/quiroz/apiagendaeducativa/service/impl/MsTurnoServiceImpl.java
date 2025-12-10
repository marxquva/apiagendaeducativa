package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.dto.request.TurnoCreateDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.TurnoResponseDto;
import com.marx.quiroz.apiagendaeducativa.entity.InstitucionEntity;
import com.marx.quiroz.apiagendaeducativa.entity.TurnoEntity;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.exception.ResourceNotFoundException;
import com.marx.quiroz.apiagendaeducativa.repository.InstitucionRepository;
import com.marx.quiroz.apiagendaeducativa.repository.MsTurnoRepository;
import com.marx.quiroz.apiagendaeducativa.service.MsTurnoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsTurnoServiceImpl implements MsTurnoService {

    private final MsTurnoRepository turnoRepository;
    private final InstitucionRepository institucionRepository;
    public MsTurnoServiceImpl(MsTurnoRepository turnoRepository, InstitucionRepository institucionRepository) {
        this.turnoRepository = turnoRepository;
        this.institucionRepository = institucionRepository;
    }

    @Override
    public List<TurnoResponseDto> obtenerTurnos() {
        //return turnoRepository.findAll();
        return turnoRepository.findAll()
                .stream()
                .map(TurnoResponseDto::new)
                .toList();
    }

    @Override
    public List<TurnoResponseDto> obtenerTurnosPorInstitucion(Integer idInstitucion) {

        List<TurnoEntity> turnos = turnoRepository.findByInstitucionEntity_IdInstitucion(idInstitucion);

        if (turnos.isEmpty()) {
            throw new ResourceNotFoundException("No existen turnos para la institución con ID " + idInstitucion);
        }

        return turnos.stream()
                .map(TurnoResponseDto::new)
                .toList();
    }

    @Override
    public TurnoResponseDto obtenerTurnoById(int id) {
        return null;
    }

    @Override
    public TurnoResponseDto obtenerTurnoPorIdInstitucion(Integer idTurno, Integer idInstitucion) {

        TurnoEntity turno = turnoRepository
                .findByIdTurnoAndInstitucionEntity_IdInstitucion(idTurno, idInstitucion)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El turno con ID " + idTurno + " no pertenece a la institución " + idInstitucion
                ));

        return new TurnoResponseDto(turno);
    }


    @Override
    public TurnoResponseDto crearTurno(TurnoCreateDto turnoDto) {

        Integer idInstitucion = turnoDto.getIdInstitucion();
        String nombreTurno = turnoDto.getNombreTurno();

        boolean existe = turnoRepository
                .existsByNombreTurnoAndInstitucionEntity_IdInstitucion(nombreTurno, idInstitucion);

        if (existe) {
            throw new InvalidOperationException(
                    "El turno '" + nombreTurno + "' ya existe en esta institución."
            );
        }

        if (turnoDto.getHoraFinal().isBefore(turnoDto.getHoraInicio())) {
            throw new InvalidOperationException("La hora final no puede ser menor que la hora inicial.");
        }

        InstitucionEntity institucion = institucionRepository.findById(idInstitucion)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "La institución con ID " + idInstitucion + " no existe."
                ));

        TurnoEntity turno = new TurnoEntity();
        turno.setNombreTurno(turnoDto.getNombreTurno());
        turno.setDescripcion(turnoDto.getDescripcion());
        turno.setHoraInicio(turnoDto.getHoraInicio());
        turno.setHoraFinal(turnoDto.getHoraFinal());
        turno.setEstado(turnoDto.getEstado());
        turno.setInstitucionEntity(institucion);

        turnoRepository.save(turno);
        return new TurnoResponseDto(turno);
    }


    @Override
    public TurnoResponseDto actualizarTurno(Integer idTurno, TurnoCreateDto turnoDto) {

        TurnoEntity turnoExistente = turnoRepository.findById(idTurno)
                .orElseThrow(() -> new ResourceNotFoundException("El turno con ID " + idTurno + " no existe."));

        Integer idInstitucion = turnoExistente.getInstitucionEntity().getIdInstitucion();
        String nuevoNombre = turnoDto.getNombreTurno();

        // VALIDACIÓN: comprobar si existe otro turno con mismo nombre en la institución
        boolean existeDuplicado = turnoRepository
                .existsByNombreTurnoAndInstitucionEntity_IdInstitucionAndIdTurnoNot(
                        nuevoNombre,
                        idInstitucion,
                        idTurno
                );

        if (existeDuplicado) {
            throw new InvalidOperationException(
                    "El nombre de turno '" + nuevoNombre + "' ya existe en esta institución."
            );
        }

        if (turnoDto.getHoraFinal().isBefore(turnoDto.getHoraInicio())) {
            throw new InvalidOperationException("La hora final no puede ser menor que la hora inicial.");
        }


        // Actualizar valores
        turnoExistente.setNombreTurno(turnoDto.getNombreTurno());
        turnoExistente.setDescripcion(turnoDto.getDescripcion());
        turnoExistente.setHoraInicio(turnoDto.getHoraInicio());
        turnoExistente.setHoraFinal(turnoDto.getHoraFinal());
        turnoExistente.setEstado(turnoDto.getEstado());

        TurnoEntity actualizado = turnoRepository.save(turnoExistente);
        return new TurnoResponseDto(actualizado);
    }

}
