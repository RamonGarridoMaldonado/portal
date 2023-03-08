package com.rgarmal.portal.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rgarmal.portal.model.Usuario;

@Service
public class UserService implements UserDetailsService {

    @Value("${url.seguridad.rest.service}")
    String urlApi;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repo.findByName(username);

        List<GrantedAuthority> roles = new ArrayList<>();

        List<Permiso> permisos = user.getPermisos();

        for (Permiso p : permisos) {
            roles.add(new SimpleGrantedAuthority(p.getNombre()));
        }

        // UserDetails userDetails = new User(user.getName(), user.getPassword(),
        // roles);
        UserDetails userDetails = User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .authorities(roles)
                .build();
        return userDetails;
    }
    }
    
}
