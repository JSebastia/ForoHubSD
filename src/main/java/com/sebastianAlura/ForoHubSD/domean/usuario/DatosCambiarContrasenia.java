package com.sebastianAlura.ForoHubSD.domean.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosCambiarContrasenia(
        @NotBlank String nombre,
        @NotBlank String contraseniaAntigua,
        @NotBlank String contraseniaNueva
) {
}
