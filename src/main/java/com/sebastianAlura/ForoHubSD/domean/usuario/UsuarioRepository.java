package com.sebastianAlura.ForoHubSD.domean.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String userEmail);

    UserDetails findByNombre(String userNombre);
}
