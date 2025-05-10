package com.tele.lab4_20222238.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 5, max = 100, message = "El nombre debe tener entre 5 y 100 caracteres.")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria.")
    @Size(min = 10, message = "La dirección debe tener al menos 10 caracteres.")
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio.")
    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener exactamente 9 dígitos.")
    private String telefono;

}
