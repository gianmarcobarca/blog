package com.barca.blogmanager.repositories;

import com.barca.blogmanager.dtos.PostResponseDto;
import com.barca.blogmanager.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;

import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {

  Optional<PostResponseDto> findFirstById(String id);

  Page<PostResponseDto> findAllBy(Pageable pageable);

  @Update("{'$inc': {'commentsSize': 1}}")
  long findAndIncrementCommentsSizeById(String postId);

  void deleteAllByUserId(String userId);
}
