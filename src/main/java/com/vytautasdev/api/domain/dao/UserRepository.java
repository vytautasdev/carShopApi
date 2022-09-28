package com.vytautasdev.api.domain.dao;

import java.util.Optional;

import com.vytautasdev.api.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
