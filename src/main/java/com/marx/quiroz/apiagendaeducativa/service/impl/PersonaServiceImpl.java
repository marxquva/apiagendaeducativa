package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.dto.request.PersonaInstitucionAddRequestDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.*;
import com.marx.quiroz.apiagendaeducativa.entity.*;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.repository.InstitucionRepository;
import com.marx.quiroz.apiagendaeducativa.repository.PersonaInstitucionRepository;
import com.marx.quiroz.apiagendaeducativa.repository.PersonaRepository;
import com.marx.quiroz.apiagendaeducativa.repository.RolRepository;
import com.marx.quiroz.apiagendaeducativa.service.PersonaService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final InstitucionRepository institucionRepository;
    private final RolRepository rolRepository;
    private final PersonaInstitucionRepository personaInstitucionRepository;
    public PersonaServiceImpl(PersonaRepository personaRepository, InstitucionRepository institucionRepository, RolRepository rolRepository, PersonaInstitucionRepository personaInstitucionRepository) {
        this.personaRepository = personaRepository;
        this.institucionRepository = institucionRepository;
        this.rolRepository = rolRepository;
        this.personaInstitucionRepository = personaInstitucionRepository;
    }

    @Override
    public PersonaEntity crearPersona(PersonaEntity persona) {
        return null;
    }

    @Override
    public List<PersonaResponseDto> obtenerPersonas() {
        return personaRepository.findAll()
                .stream()
                .map(persona -> mapToPersonaResponseDTO(persona, null))
                .toList();
    }

    @Override
    public Page<PersonaResponseDto> obtenerPersonasPaginado(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<PersonaEntity> personasPage = personaRepository.findAll(pageable);

        return personasPage.map(persona -> mapToPersonaResponseDTO(persona, null));
    }

    @Override
    public List<PersonaResponseDto> obtenerPersonasPorInstitucion(Integer idInstitucion) {

        return personaRepository.findAllByInstitucion(idInstitucion)
                .stream()
                .map(persona -> mapToPersonaResponseDTO(persona, idInstitucion))
                .toList();
    }

    @Override
    public PersonaResponseDto obtenerPersonaPorInstitucion(Integer idInstitucion, Integer idPersona) {

        PersonaEntity persona = personaRepository.findByIdAndInstitucion(idPersona, idInstitucion)
                .orElseThrow(() -> new InvalidOperationException(
                        "La persona no pertenece a la institución especificada."
                ));

        return mapToPersonaResponseDTO(persona, idInstitucion);
    }

    @Override
    public PersonaResponseDto obtenerPersonaPorNumeroDocumento(String numeroDocumento) {
        PersonaEntity persona = personaRepository.findByNumeroDocumento(numeroDocumento)
                .orElseThrow(() -> new InvalidOperationException("La persona no existe"));

        return mapToPersonaResponseDTO(persona, null);
    }

    @Override
    public PersonaResponseDto obtenerPersonaPorDocumentoEInstitucion(String numeroDocumento, Integer idInstitucion) {

        PersonaEntity persona = personaRepository.findByNumeroDocumento(numeroDocumento)
                .orElseThrow(() -> new InvalidOperationException("La persona no existe"));

        boolean pertenece = persona.getPersonaInstituciones() != null &&
                persona.getPersonaInstituciones().stream()
                        .anyMatch(pi -> pi.getInstitucion() != null &&
                                pi.getInstitucion().getIdInstitucion().equals(idInstitucion));

        if (!pertenece) {
            throw new InvalidOperationException("La persona no pertenece a la institución indicada");
        }

        return mapToPersonaResponseDTO(persona, idInstitucion);
    }

    @Override
    @Transactional
    public PersonaInstitucionAddResponseDto agregarPersonaAInstitucion(PersonaInstitucionAddRequestDto dto) {

        // 1. Persona válida
        PersonaEntity persona = personaRepository.findById(dto.getIdPersona())
                .orElseThrow(() -> new InvalidOperationException("La persona no existe"));

        // 2. Institución válida
        InstitucionEntity institucion = institucionRepository.findById(dto.getIdInstitucion())
                .orElseThrow(() -> new InvalidOperationException("La institución no existe"));

        // 3. Rol académico válido
        RolEntity rol = rolRepository.findById(dto.getIdRolAcademico())
                .orElseThrow(() -> new InvalidOperationException("El rol académico no existe"));

        // 4. Validar si ya pertenece con el MISMO rol
        boolean yaTieneMismoRol = personaInstitucionRepository
                .existsByPersonaIdPersonaAndInstitucionIdInstitucionAndRolAcademicoIdRolAcademico(
                        dto.getIdPersona(),
                        dto.getIdInstitucion(),
                        dto.getIdRolAcademico()
                );

        if (yaTieneMismoRol) {
            throw new InvalidOperationException("La persona ya tiene este mismo rol en esta institución.");
        }


        // 5. Crear la relación
        PersonaInstitucionEntity nuevaRelacion = new PersonaInstitucionEntity();
        nuevaRelacion.setPersona(persona);
        nuevaRelacion.setInstitucion(institucion);
        nuevaRelacion.setRolAcademico(rol);

        personaInstitucionRepository.save(nuevaRelacion);

        // 6. Respuesta
        return new PersonaInstitucionAddResponseDto(
                persona.getIdPersona(),
                institucion.getIdInstitucion(),
                rol.getIdRolAcademico(),
                rol.getNombre()
        );
    }

    @Override
    @Transactional
    public PersonaInstitucionAddResponseDto actualizarPersonaInstitucion(Integer idPersonaInstitucion, PersonaInstitucionAddRequestDto dto) {
        // 1. Buscar la relación existente
        PersonaInstitucionEntity relacion = personaInstitucionRepository.findById(idPersonaInstitucion)
                .orElseThrow(() -> new InvalidOperationException("La relación Persona–Institución no existe"));

        // 2. Validar persona (debe coincidir con la relación actual)
        if (!relacion.getPersona().getIdPersona().equals(dto.getIdPersona())) {
            throw new InvalidOperationException("El ID de persona no coincide con la relación existente");
        }

        // 3. Validar institución (opcional, si quieres permitir cambio de institución)
        if (!relacion.getInstitucion().getIdInstitucion().equals(dto.getIdInstitucion())) {
            InstitucionEntity institucion = institucionRepository.findById(dto.getIdInstitucion())
                    .orElseThrow(() -> new InvalidOperationException("La institución no existe"));
            relacion.setInstitucion(institucion);
        }

        // 4. Validar rol académico
        RolEntity rol = rolRepository.findById(dto.getIdRolAcademico())
                .orElseThrow(() -> new InvalidOperationException("El rol académico no existe"));

        // 5. Verificar que no exista otra relación con ese mismo rol en la institución
        boolean yaExisteRol = personaInstitucionRepository
                .findByPersona_IdPersonaAndInstitucion_IdInstitucion(relacion.getPersona().getIdPersona(), relacion.getInstitucion().getIdInstitucion())
                .stream()
                .anyMatch(r -> !r.getIdPersonaInstitucion().equals(idPersonaInstitucion)
                        && r.getRolAcademico().getIdRolAcademico().equals(dto.getIdRolAcademico()));

        if (yaExisteRol) {
            throw new InvalidOperationException("La persona ya tiene asignado este rol en esta institución");
        }

        relacion.setRolAcademico(rol);
        relacion.setFechaModificacion(java.time.LocalDateTime.now());

        personaInstitucionRepository.save(relacion);

        // 7. Respuesta
        return new PersonaInstitucionAddResponseDto(
                relacion.getPersona().getIdPersona(),
                relacion.getInstitucion().getIdInstitucion(),
                rol.getIdRolAcademico(),
                rol.getNombre()
        );
    }

    @Override
    @Transactional
    public void eliminarPersonaInstitucion(Integer idPersonaInstitucion) {
        PersonaInstitucionEntity relacion = personaInstitucionRepository.findById(idPersonaInstitucion)
                .orElseThrow(() -> new InvalidOperationException("La relación Persona–Institución no existe"));

        personaInstitucionRepository.delete(relacion);
    }


    private PersonaResponseDto mapToPersonaResponseDTO(PersonaEntity persona, Integer idInstitucion) {

        PersonaResponseDto personaDto = new PersonaResponseDto();
        personaDto.setIdPersona(persona.getIdPersona());
        personaDto.setNombre(persona.getNombre());
        personaDto.setApellidos(persona.getApellido());
        personaDto.setSexo(persona.getSexo());
        personaDto.setDireccion(persona.getDireccion());
        personaDto.setTelefono(persona.getTelefono());
        personaDto.setEmail(persona.getEmail());
        personaDto.setFechaNacimiento(persona.getFechaNacimiento() != null ? persona.getFechaNacimiento().toString() : null);
        personaDto.setIdTipoDocumento(persona.getTipoDocumento() != null ? persona.getTipoDocumento().getIdTipoDocumento() : null);
        personaDto.setNumeroDocumento(persona.getNumeroDocumento());

        UsuarioEntity usuario = persona.getUsuarios() != null && !persona.getUsuarios().isEmpty()
                ? persona.getUsuarios().get(0)
                : null;

        if (usuario != null) {
            UsuarioResponseDto usuarioDTO = new UsuarioResponseDto();
            usuarioDTO.setIdUsuario(usuario.getIdUsuario());
            usuarioDTO.setUsername(usuario.getUsername());

            List<PerfilSistemaResponseDto> perfiles = usuario.getUsuarioInstituciones() != null
                    ? usuario.getUsuarioInstituciones().stream()
                    .filter(ui -> idInstitucion == null ||
                            (ui.getInstitucion() != null && ui.getInstitucion().getIdInstitucion().equals(idInstitucion)))
                    .map(ui -> new PerfilSistemaResponseDto(
                            ui.getPerfilSistema(),
                            ui.getInstitucion() != null ? ui.getInstitucion().getIdInstitucion() : null
                    ))
                    .collect(Collectors.toList())
                    : List.of();

            usuarioDTO.setPerfilesSistema(perfiles);
            personaDto.setUsuario(usuarioDTO);
        }

        List<RolAcademicoResponseDto> roles = persona.getPersonaInstituciones() != null
                ? persona.getPersonaInstituciones().stream()
                .filter(pi -> idInstitucion == null ||
                        (pi.getInstitucion() != null && pi.getInstitucion().getIdInstitucion().equals(idInstitucion)))
                .map(pi -> new RolAcademicoResponseDto(
                        pi.getRolAcademico(),
                        pi.getInstitucion() != null ? pi.getInstitucion().getIdInstitucion() : null
                ))
                .collect(Collectors.toList())
                : List.of();

        personaDto.setRolesAcademicos(roles);

        return personaDto;
    }

}
