package com.ramirezdev.simple_finances.service;

import com.ramirezdev.simple_finances.domain.user.User;
import com.ramirezdev.simple_finances.domain.user.UserReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @Autowired
    private UserReposiroty userReposiroty;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Long findUserById(Long id) {
        var user = userReposiroty.getReferenceById(id);
        return user.getId();
    }

    public User findUser(Long id) {
        return userReposiroty.getReferenceById(id);
    }

    public void registerUser(User usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        userReposiroty.save(usuario);
    }


    public void deleteUser(Long id) {
        userReposiroty.deleteById(id);
    }

    public String verifyUser(User user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha()));
        if (authentication.isAuthenticated()){
            System.out.println("Success!");
            return "success!";
        }
        System.out.println("Fail!");
        return "not success";

    }
}
