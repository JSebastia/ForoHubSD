package com.sebastianAlura.ForoHubSD.domean.topico;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosAgregarTopicoDTO(
        @NotNull @JsonAlias("idUser") Long idUsuario,
        @NotBlank @JsonAlias("title") String titulo,
        @NotBlank @JsonAlias("message") String mensaje,
        @NotBlank @JsonAlias("name_course") String nombreCurso
) {
}
