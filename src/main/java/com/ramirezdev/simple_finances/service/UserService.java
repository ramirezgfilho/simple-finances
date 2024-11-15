package com.ramirezdev.simple_finances.service;

import com.ramirezdev.simple_finances.domain.user.User;
import com.ramirezdev.simple_finances.domain.user.UserDTO;
import com.ramirezdev.simple_finances.domain.user.UserReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void registerUser(UserDTO userDTO) {
        var usuario = new User(userDTO);
        if(userReposiroty.findByLogin(usuario.getLogin()) != null) {
            System.out.println("Já existe usuário com este e-mail");
            return;
        } else {
            System.out.println("Usuário cadastrado");
            usuario.setSenha(encoder.encode(usuario.getSenha()));
            userReposiroty.save(usuario);
        }
    }


    public void deleteUser(Long id) {
        userReposiroty.deleteById(id);
    }

//    public String verifyUser(User user) {
//        Authentication authentication =
//                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha()));
//        if (authentication.isAuthenticated()){
//            System.out.println("Success!");
//            return "success!";
//        }
//        System.out.println("Fail!");
//        return "not success";
//    }

    public User findByLogin(String login) {
        return userReposiroty.findByLogin(login);
    }

    public List<User> findAll() {
        return userReposiroty.findAll();
    }

    public boolean alteraSenha(String senhaatual, String novasenha, String name) {
        User userSenha = userReposiroty.findByLogin(name);
        if (encoder.matches(senhaatual, userSenha.getSenha())){
            userSenha.setSenha(encoder.encode(novasenha));
            userReposiroty.save(userSenha);
            System.out.println("Senha alterada com sucesso.");
            return true;
        } else {
            System.out.println("Senha não alterada.");
            return false;
        }

    }
}
