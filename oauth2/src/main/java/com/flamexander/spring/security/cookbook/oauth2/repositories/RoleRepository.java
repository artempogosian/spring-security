package com.flamexander.spring.security.cookbook.oauth2.repositories;

import com.flamexander.spring.security.cookbook.oauth2.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
