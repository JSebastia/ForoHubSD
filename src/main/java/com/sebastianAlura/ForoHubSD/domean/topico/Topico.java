package com.sebastianAlura.ForoHubSD.domean.topico;

import com.sebastianAlura.ForoHubSD.domean.curso.Curso;
import com.sebastianAlura.ForoHubSD.domean.respuesta.Respuesta;
import com.sebastianAlura.ForoHubSD.domean.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity(name = "Topico")
@Table(name = "topicos")
public class Topico {
    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso")
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuesta;

    //METODOS
    public Topico(@Valid DatosAgregarTopicoDTO datosAgregarTopico, Usuario autor, Curso curso) {
        this.titulo = datosAgregarTopico.titulo();
        this.mensaje = datosAgregarTopico.mensaje();
        this.fecha = LocalDateTime.now();
        this.status = false;
        this.autor = autor;
        this.curso = curso;
    }

    public void actualizarDatosTopico(DatosAgregarTopicoDTO datosActualizarTopico) {
        this.titulo = datosActualizarTopico.titulo();
        this.mensaje = datosActualizarTopico.mensaje();
    }
}
