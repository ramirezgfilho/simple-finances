package com.ramirezdev.simple_finances.service;

import com.ramirezdev.simple_finances.config.EmailService;
import com.ramirezdev.simple_finances.domain.user.User;
import com.ramirezdev.simple_finances.domain.user.UserReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {


    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserReposiroty userReposiroty;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);



    public void handlePasswordRecovery(String email) {

        User user = userService.findByLogin(email);

        if(user != null){
            user.setSenha(encoder.encode("HksheriZ_*1"));
            userReposiroty.save(user);
            emailService.sendEmail(email,"Sua nova senha, para a sua segurança, altere assim que fizer login.", "Sua senha provisória é : HksheriZ_*1  Por favor, acesse o sistema e troque a senha imediatamente.");
        } else {
            System.out.println("Usuário não encontrado.");
        }



    }
}
