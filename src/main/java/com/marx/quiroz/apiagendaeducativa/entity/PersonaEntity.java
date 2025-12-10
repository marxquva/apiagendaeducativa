package com.marx.quiroz.apiagendaeducativa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="sis_persona")
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Column(name = "apellido", length = 120)
    private String apellido;

    @Column(name = "sexo", length = 10)
    private String sexo;

    @Column(name = "direccion", length = 120)
    private String direccion;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "email", length = 120, unique = true)
    private String email;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "numero_documento", length = 15, unique = true)
    private String numeroDocumento;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "estado")
    private Integer estado = 1;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento_fk", referencedColumnName = "id_tipo_documento")
    private TipoDocumentoEntity tipoDocumento;

    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private List<PersonaInstitucionEntity> personaInstituciones = new ArrayList<>();

    @OneToMany(mappedBy = "persona")
    private List<UsuarioEntity> usuarios;



}
