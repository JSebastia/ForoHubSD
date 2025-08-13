package com.sebastianAlura.ForoHubSD.domean.respuesta;

import com.sebastianAlura.ForoHubSD.domean.topico.Topico;
import com.sebastianAlura.ForoHubSD.domean.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity(name = "Respuesta")
@Table(name = "respuestas")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;
    private LocalDateTime fecha;
    private boolean solucion;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Topico topico;
}
