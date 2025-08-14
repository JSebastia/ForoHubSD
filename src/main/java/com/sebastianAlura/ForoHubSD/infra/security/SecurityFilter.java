package com.sebastianAlura.ForoHubSD.infra.security;

import com.sebastianAlura.ForoHubSD.domean.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    //ATRIBUTOS
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    //METODOS
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = capturarToken(request);

        if (token != null) {
            var subject = tokenService.getSubject(token);
            var usuario = usuarioRepository.findByEmail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            //Le dice a Spring que el usuario esta Autenticado.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String capturarToken(HttpServletRequest request) {
        var autorizacionHeader = request.getHeader("Authorization");

        if (autorizacionHeader != null) {
            return autorizacionHeader.replace("Bearer ", "");
        }
        return null;
    }
}
