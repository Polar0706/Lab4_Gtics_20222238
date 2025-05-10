package com.tele.lab4_20222238.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 5, max = 100, message = "El nombre debe tener entre 5 y 100 caracteres.")
    private String nombreCompleto;

    @NotBlank(message = "El DNI es obligatorio.")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos.")
    private String dni;

    @NotBlank(message = "El teléfono es obligatorio.")
    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener exactamente 9 dígitos.")
    private String telefono;

    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "Debe ingresar un correo válido.")
    private String correo;

    @NotNull(message = "Debe ingresar la fecha de incorporación.")
    @PastOrPresent(message = "La fecha no puede ser futura.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaIncorporacion;

    @NotBlank(message = "Debe seleccionar un estado.")
    private String estado;
}
