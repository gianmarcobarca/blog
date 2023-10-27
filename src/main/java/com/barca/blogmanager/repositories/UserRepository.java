package com.barca.blogmanager.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.barca.blogmanager.models.User;

public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findByEmail(String email);
}
