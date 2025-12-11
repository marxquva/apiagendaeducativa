package com.marx.quiroz.apiagendaeducativa.service.impl;

import com.marx.quiroz.apiagendaeducativa.dto.request.RegistroRequestDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.PerfilSistemaResponseDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.PersonaResponseDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.RolAcademicoResponseDto;
import com.marx.quiroz.apiagendaeducativa.dto.response.UsuarioResponseDto;
import com.marx.quiroz.apiagendaeducativa.entity.*;
import com.marx.quiroz.apiagendaeducativa.exception.InvalidOperationException;
import com.marx.quiroz.apiagendaeducativa.repository.*;
import com.marx.quiroz.apiagendaeducativa.service.RegistroService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroServiceImpl implements RegistroService {
    private final PersonaRepository personaRepository;
    private final UsuarioRepository usuarioRepository;
    private final InstitucionRepository institucionRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final PersonaInstitucionRepository personaInstitucionRepository;
    private final UsuarioInstitucionRepository usuarioInstitucionRepository;
    private final RolRepository rolRepository;
    private final PerfilRepository perfilRepository;

    private final Integer ROL_POSTULANTE = 7;
    private final Integer PERFIL_INVITADO = 5;

    public RegistroServiceImpl(PersonaRepository personaRepository, UsuarioRepository usuarioRepository, InstitucionRepository institucionRepository, TipoDocumentoRepository tipoDocumentoRepository, PersonaInstitucionRepository personaInstitucionRepository, UsuarioInstitucionRepository usuarioInstitucionRepository, RolRepository rolRepository, PerfilRepository perfilRepository) {
        this.personaRepository = personaRepository;
        this.usuarioRepository = usuarioRepository;
        this.institucionRepository = institucionRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
        this.personaInstitucionRepository = personaInstitucionRepository;
        this.usuarioInstitucionRepository = usuarioInstitucionRepository;
        this.rolRepository = rolRepository;
        this.perfilRepository = perfilRepository;
    }

    @Override
    @Transactional
    public PersonaResponseDto registrarPersonaUsuario(RegistroRequestDto registroDto) {

        PersonaEntity persona;
        Optional<PersonaEntity> personaOptional = personaRepository.findByNumeroDocumento(registroDto.getNumeroDocumento());
        System.out.println(registroDto);
        // Validación de existencia de persona
        if (personaOptional.isPresent()) {
            persona = personaOptional.get();

            // Validar si ya tiene usuario
            Optional<UsuarioEntity> usuarioExistente = usuarioRepository.findByPersona(persona);

            if (usuarioExistente.isPresent()) {
                throw new InvalidOperationException("La persona y usuario ya existen.");
            }

        } else {
            // Crear persona nueva
            persona = new PersonaEntity();
            persona.setNombre(registroDto.getNombre());
            persona.setApellido(registroDto.getApellidos());
            persona.setSexo(registroDto.getSexo());
            persona.setDireccion(registroDto.getDireccion());
            persona.setTelefono(registroDto.getTelefono());
            persona.setEmail(registroDto.getEmail());
            persona.setFechaNacimiento(registroDto.getFechaNacimiento());
            persona.setNumeroDocumento(registroDto.getNumeroDocumento());

            TipoDocumentoEntity tipoDoc = tipoDocumentoRepository.findById(registroDto.getIdTipoDocumento())
                    .orElseThrow(() -> new InvalidOperationException("Tipo documento no existe"));

            persona.setTipoDocumento(tipoDoc);
            persona = personaRepository.save(persona);
        }

        // Crear Usuario
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsername(registroDto.getUsername());
        usuario.setPasswordHash(new BCryptPasswordEncoder().encode(registroDto.getPassword()));
        //usuarioEntity.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        usuario.setPersona(persona);
        usuario = usuarioRepository.save(usuario);

        // Asociar Persona a Institución
        if (!personaInstitucionRepository.existsByPersonaIdPersonaAndInstitucionIdInstitucion(
                persona.getIdPersona(), registroDto.getIdInstitucion())) {

            RolEntity rol = rolRepository.findById(ROL_POSTULANTE)
                    .orElseThrow(() -> new InvalidOperationException("Rol académico no existe"));

            PersonaInstitucionEntity pi = new PersonaInstitucionEntity();
            pi.setPersona(persona);
            pi.setInstitucion(institucionRepository.findById(registroDto.getIdInstitucion())
                    .orElseThrow(() -> new InvalidOperationException("Institución no existe")));
            pi.setRolAcademico(rol);

            personaInstitucionRepository.save(pi);
            persona.getPersonaInstituciones().add(pi);

        }

        // Asociar Usuario a Institución
        if (!usuarioInstitucionRepository.existsByUsuarioIdUsuarioAndInstitucionIdInstitucion(
                usuario.getIdUsuario(), registroDto.getIdInstitucion())) {

            PerfilEntity perfil = perfilRepository.findById(PERFIL_INVITADO)
                    .orElseThrow(() -> new InvalidOperationException("Perfil de sistema no existe"));

            UsuarioInstitucionEntity ui = new UsuarioInstitucionEntity();
            ui.setUsuario(usuario);
            ui.setInstitucion(institucionRepository.findById(registroDto.getIdInstitucion())
                    .orElseThrow(() -> new InvalidOperationException("Institución no existe")));
            ui.setPerfilSistema(perfil);

            usuarioInstitucionRepository.save(ui);
            usuario.getUsuarioInstituciones().add(ui);
        }

        // Construir DTO de respuesta
        PersonaResponseDto response = new PersonaResponseDto();
        response.setIdPersona(persona.getIdPersona());
        response.setNombre(persona.getNombre());
        response.setApellidos(persona.getApellido());
        response.setSexo(persona.getSexo());
        response.setDireccion(persona.getDireccion());
        response.setTelefono(persona.getTelefono());
        response.setEmail(persona.getEmail());
        response.setFechaNacimiento(persona.getFechaNacimiento() != null ? persona.getFechaNacimiento().toString() : null);
        response.setIdTipoDocumento(persona.getTipoDocumento().getIdTipoDocumento());
        response.setNumeroDocumento(persona.getNumeroDocumento());

        // Usuario
        UsuarioResponseDto usuarioDTO = new UsuarioResponseDto();
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setUsername(usuario.getUsername());

        // Mapear perfiles de sistema asociados al usuario
        List<PerfilSistemaResponseDto> perfiles = usuario.getUsuarioInstituciones() != null
                ? usuario.getUsuarioInstituciones()
                .stream()
                .map(ui -> new PerfilSistemaResponseDto(
                        ui.getPerfilSistema(),
                        ui.getInstitucion() != null ? ui.getInstitucion().getIdInstitucion() : null
                ))
                .toList()
                : List.of(); // lista vacía si es null

        usuarioDTO.setPerfilesSistema(perfiles);


        // Mapear roles académicos asociados a la persona
        List<RolAcademicoResponseDto> roles = persona.getPersonaInstituciones() != null
                ? persona.getPersonaInstituciones()
                .stream()
                .map(pi -> new RolAcademicoResponseDto(
                        pi.getRolAcademico(),
                        pi.getInstitucion() != null ? pi.getInstitucion().getIdInstitucion() : null
                ))
                .toList()
                : List.of(); // lista vacía si es null

        response.setUsuario(usuarioDTO);
        response.setRolesAcademicos(roles);

        return response;
    }
}
