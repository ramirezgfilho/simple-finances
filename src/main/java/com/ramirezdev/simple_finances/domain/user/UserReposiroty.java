package com.ramirezdev.simple_finances.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReposiroty extends JpaRepository<User,Long>{
}
