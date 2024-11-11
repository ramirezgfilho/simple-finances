package com.ramirezdev.simple_finances.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailService implements UserDetailsService {


    @Autowired
    private UserReposiroty userReposiroty;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userReposiroty.findByLogin(username);


        if(user == null) {
            throw new UsernameNotFoundException("Verifique o login ou senha!");
        }

        return new UserPrincipal(user);
    }
}
