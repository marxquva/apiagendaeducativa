package com.marx.quiroz.apiagendaeducativa.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RegistroRequestDto {

    @NotNull(message = "El nombre de persona es obligatorio")
    @Size(min = 3, max = 120, message = "El nombre debe tener entre 3 y 120 caracteres")
    private String nombre;
    private String apellidos;

    @NotBlank(message = "El sexo es obligatorio (M/F)")
    @Pattern(regexp = "M|F", message = "El sexo debe ser M o F")
    private String sexo;

    private String direccion;

    @Pattern(regexp = "\\d{7,15}", message = "El teléfono debe contener entre 7 y 15 dígitos")
    private String telefono;

    @NotBlank(message = "El email es obligatorio")
    @Email
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;


    @NotNull(message = "El tipo de documento es obligatorio")
    @Positive(message = "El tipo de documento debe ser un número positivo")
    private Integer idTipoDocumento;

    @NotBlank(message = "El número de documento es obligatorio")
    @Size(min = 6, max = 15, message = "El número de documento debe tener entre 6 y 15 caracteres")
    private String numeroDocumento;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 4, max = 50, message = "El nombre de usuario debe tener entre 4 y 50 caracteres")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener mínimo 6 caracteres")
    private String password;

    @NotNull(message = "El ID de la institución es obligatorio")
    @Positive(message = "El ID de la institución debe de ser un numero positivo")
    private Integer idInstitucion;
}
