package com.sebastianAlura.ForoHubSD.controller;

import com.sebastianAlura.ForoHubSD.domean.curso.CursoRepository;
import com.sebastianAlura.ForoHubSD.domean.topico.*;
import com.sebastianAlura.ForoHubSD.domean.usuario.Usuario;
import com.sebastianAlura.ForoHubSD.domean.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    //ATRIBUTOS
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    //METODOS
    @Transactional
    @PostMapping
    public ResponseEntity<DatosDetalleTopico> agregarTopico(@RequestBody @Valid DatosAgregarTopicoDTO datosAgregarTopico, UriComponentsBuilder uriComponentsBuilder) {
        Optional<Usuario> autor = usuarioRepository.findById(datosAgregarTopico.idUsuario());
        var curso = cursoRepository.findByNombre(datosAgregarTopico.nombreCurso());
        Topico topico;

        if (autor.isPresent() && curso != null) {
            topico = new Topico(datosAgregarTopico, autor.get(), curso);
        } else {
            throw new EntityNotFoundException();
        }

        topicoRepository.save(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listarTopicos(@PageableDefault(size = 10, sort = {"fecha"}) Pageable pageable) {
        var page = topicoRepository.findAll(pageable);

        return ResponseEntity.ok(page.map(DatosListaTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListaTopico> listarTopicoId(@PathVariable(name = "id") Long idTopico) {
        var topico = topicoRepository.getReferenceById(idTopico);

        return ResponseEntity.ok(new DatosListaTopico(topico));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> actualizarTopico(
            @PathVariable(name = "id") Long idTopico,
            @RequestBody @Valid DatosAgregarTopicoDTO datosActualizarTopico) {

        var topico = topicoRepository.getReferenceById(idTopico);

        //Se verifica que el "nombre" del curso coincida con el recibido y verifica que el "id" del autor coincida con el recibido.
        if (topico.getCurso().getNombre().equals(datosActualizarTopico.nombreCurso()) && topico.getAutor().getId().equals(datosActualizarTopico.idUsuario())) {
            topico.actualizarDatosTopico(datosActualizarTopico);
        } else {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopicoId(@PathVariable(name = "id") Long idTopico) {
        Optional<Topico> topico = topicoRepository.findById(idTopico);

        if (topico.isPresent()){
            topicoRepository.deleteById(idTopico);
        } else {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.noContent().build();
    }
}
