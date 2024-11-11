package com.ramirezdev.simple_finances.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserReposiroty extends JpaRepository<User,Long>{


    User findByLogin(String login);
}
