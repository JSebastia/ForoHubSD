package com.sebastianAlura.ForoHubSD.domean.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosNewUsuario(
        @NotBlank String nombre,
        @NotBlank String email,
        @NotBlank String contrasenia) {
}
