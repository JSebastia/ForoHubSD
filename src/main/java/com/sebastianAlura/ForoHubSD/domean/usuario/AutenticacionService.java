package com.sebastianAlura.ForoHubSD.domean.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {
    //ATRIBUTOS
    @Autowired
    private UsuarioRepository usuarioRepository;

    //METODOS
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByEmail(username);

        if (usuario != null) {//El usuario puede loguearse con el Nombre o con el Email.
            return usuario;
        }
        return usuarioRepository.findByNombre(username);
    }
}
