package com.barca.blogmanager.repositories;

import com.barca.blogmanager.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
