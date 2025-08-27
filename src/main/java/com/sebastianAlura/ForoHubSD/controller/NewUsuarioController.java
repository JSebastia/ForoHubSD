package com.sebastianAlura.ForoHubSD.controller;

import com.sebastianAlura.ForoHubSD.domean.usuario.DatosNewUsuario;
import com.sebastianAlura.ForoHubSD.domean.usuario.DatosNewUsuarioCreado;
import com.sebastianAlura.ForoHubSD.domean.usuario.Usuario;
import com.sebastianAlura.ForoHubSD.domean.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/new-usuario")
public class NewUsuarioController {
    //ATRIBUTOS
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //METODOS
    @Transactional
    @PostMapping
    public ResponseEntity<DatosNewUsuarioCreado> NewUsuario(@RequestBody @Valid DatosNewUsuario datosNewUsuario, UriComponentsBuilder uriComponentsBuilder) {
        var contrasenia = passwordEncoder.encode(datosNewUsuario.contrasenia());
        Usuario usuario = new Usuario(null, datosNewUsuario.nombre(), datosNewUsuario.email(), contrasenia);

        usuarioRepository.save(usuario);

        var uri = uriComponentsBuilder.path("/new-usuario/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosNewUsuarioCreado(usuario.getNombre(), usuario.getEmail()));
    }
}
