package com.flamexander.spring.security.cookbook.oauth2.repositories;


import com.flamexander.spring.security.cookbook.oauth2.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
