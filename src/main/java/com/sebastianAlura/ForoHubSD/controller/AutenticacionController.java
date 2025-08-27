package com.sebastianAlura.ForoHubSD.controller;

import com.sebastianAlura.ForoHubSD.domean.usuario.DatosCambiarContrasenia;
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
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    //METODOS
    @PostMapping
    public ResponseEntity<DatosTokenJWT> iniciarSesion(@RequestBody @Valid DatosInicioSesion datosLogin) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datosLogin.nombre(), datosLogin.contrasenia());
        var autenticacion = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }

    @Transactional
    @PostMapping("/cambiar-contraseña")
    public ResponseEntity cambiarContrasenia(@RequestBody @Valid DatosCambiarContrasenia datosCambiarContrasenia) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datosCambiarContrasenia.nombre(), datosCambiarContrasenia.contraseniaAntigua());
        var autenticacion = authenticationManager.authenticate(authenticationToken);

        if (autenticacion.isAuthenticated()) {
            Usuario usuario = (Usuario) autenticacion.getPrincipal();
            usuario.actualizarContrasenia(passwordEncoder.encode(datosCambiarContrasenia.contraseniaNueva()));
        }
        return ResponseEntity.ok().build();
    }
}
