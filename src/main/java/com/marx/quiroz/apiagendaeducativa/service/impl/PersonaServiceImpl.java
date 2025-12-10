package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.dto.request.PersonaInstitucionAddRequestDTO;
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
    public List<PersonaResponseDTO> obtenerPersonas() {
        return personaRepository.findAll()
                .stream()
                .map(persona -> mapToPersonaResponseDTO(persona, null))
                .toList();
    }

    @Override
    public Page<PersonaResponseDTO> obtenerPersonasPaginado(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<PersonaEntity> personasPage = personaRepository.findAll(pageable);

        return personasPage.map(persona -> mapToPersonaResponseDTO(persona, null));
    }

    @Override
    public List<PersonaResponseDTO> obtenerPersonasPorInstitucion(Integer idInstitucion) {

        return personaRepository.findAllByInstitucion(idInstitucion)
                .stream()
                .map(persona -> mapToPersonaResponseDTO(persona, idInstitucion))
                .toList();
    }

    @Override
    public PersonaResponseDTO obtenerPersonaPorInstitucion(Integer idInstitucion, Integer idPersona) {

        PersonaEntity persona = personaRepository.findByIdAndInstitucion(idPersona, idInstitucion)
                .orElseThrow(() -> new InvalidOperationException(
                        "La persona no pertenece a la institución especificada."
                ));

        return mapToPersonaResponseDTO(persona, idInstitucion);
    }

    @Override
    public PersonaResponseDTO obtenerPersonaPorNumeroDocumento(String numeroDocumento) {
        PersonaEntity persona = personaRepository.findByNumeroDocumento(numeroDocumento)
                .orElseThrow(() -> new InvalidOperationException("La persona no existe"));

        return mapToPersonaResponseDTO(persona, null);
    }

    @Override
    public PersonaResponseDTO obtenerPersonaPorDocumentoEInstitucion(String numeroDocumento, Integer idInstitucion) {

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
    public PersonaInstitucionAddResponseDTO agregarPersonaAInstitucion(PersonaInstitucionAddRequestDTO dto) {

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
        return new PersonaInstitucionAddResponseDTO(
                persona.getIdPersona(),
                institucion.getIdInstitucion(),
                rol.getIdRolAcademico(),
                rol.getNombre()
        );
    }

    private PersonaResponseDTO mapToPersonaResponseDTO(PersonaEntity persona, Integer idInstitucion) {

        PersonaResponseDTO personaDto = new PersonaResponseDTO();
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
            UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO();
            usuarioDTO.setIdUsuario(usuario.getIdUsuario());
            usuarioDTO.setUsername(usuario.getUsername());

            List<PerfilSistemaResponseDTO> perfiles = usuario.getUsuarioInstituciones() != null
                    ? usuario.getUsuarioInstituciones().stream()
                    .filter(ui -> idInstitucion == null ||
                            (ui.getInstitucion() != null && ui.getInstitucion().getIdInstitucion().equals(idInstitucion)))
                    .map(ui -> new PerfilSistemaResponseDTO(
                            ui.getPerfilSistema(),
                            ui.getInstitucion() != null ? ui.getInstitucion().getIdInstitucion() : null
                    ))
                    .collect(Collectors.toList())
                    : List.of();

            usuarioDTO.setPerfilesSistema(perfiles);
            personaDto.setUsuario(usuarioDTO);
        }

        List<RolAcademicoResponseDTO> roles = persona.getPersonaInstituciones() != null
                ? persona.getPersonaInstituciones().stream()
                .filter(pi -> idInstitucion == null ||
                        (pi.getInstitucion() != null && pi.getInstitucion().getIdInstitucion().equals(idInstitucion)))
                .map(pi -> new RolAcademicoResponseDTO(
                        pi.getRolAcademico(),
                        pi.getInstitucion() != null ? pi.getInstitucion().getIdInstitucion() : null
                ))
                .collect(Collectors.toList())
                : List.of();

        personaDto.setRolesAcademicos(roles);

        return personaDto;
    }

}
