package com.sebastianAlura.ForoHubSD.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class GestorDeErrores {

    //METODOS
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<DatosErrorTopicosDuplicadosDTO> tratarError400_escenarioDatosTopicosDuplicados() {
        return ResponseEntity.badRequest().body(new DatosErrorTopicosDuplicadosDTO("Datos duplicados: el Titulo o el Mensaje del Topico, ya existen."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DatosErrorDatosVaciosDTO>> tratarError400_datosVacios(MethodArgumentNotValidException datosExeption) {
        var errores = datosExeption.getFieldErrors();

        List<DatosErrorDatosVaciosDTO> listaExeptions = errores.stream().map(E -> new DatosErrorDatosVaciosDTO(E.getField(), E.getDefaultMessage())).toList();

        return ResponseEntity.badRequest().body(listaExeptions);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404() {
        return ResponseEntity.notFound().build();
    }

    public record DatosErrorDatosVaciosDTO(String campo, String mensaje) {}

    public record DatosErrorTopicosDuplicadosDTO(String mensaje){}
}
