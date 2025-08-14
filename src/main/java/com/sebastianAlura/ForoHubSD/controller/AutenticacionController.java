package com.sebastianAlura.ForoHubSD.controller;

import com.sebastianAlura.ForoHubSD.domean.usuario.DatosInicioSesion;
import com.sebastianAlura.ForoHubSD.domean.usuario.Usuario;
import com.sebastianAlura.ForoHubSD.infra.security.DatosTokenJWT;
import com.sebastianAlura.ForoHubSD.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    //ATRIBUTOS
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

//    @Autowired
//    private PasswordEncoder passwordEncoder; ------ PRUEBA HASHING CONTRASEÑA NUEVO USUARIO INGRESADO ------

    //METODOS
    @PostMapping
    public ResponseEntity<DatosTokenJWT> iniciarSesion(@RequestBody @Valid DatosInicioSesion datosLogin) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datosLogin.nombre(), datosLogin.contrasenia());
        var autenticacion = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

//        System.out.println(passwordEncoder.encode(datosLogin.contrasenia())); ------ PRUEBA HASHING CONTRASEÑA NUEVO USUARIO INGRESADO ------

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}
