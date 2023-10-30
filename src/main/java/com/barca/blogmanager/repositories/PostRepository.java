package com.barca.blogmanager.repositories;

import com.barca.blogmanager.dtos.PostDeletionDto;
import com.barca.blogmanager.dtos.PostResponseDto;
import com.barca.blogmanager.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {

  Optional<PostResponseDto> findFirstById(String postId);

  Optional<PostDeletionDto> findTopById(String postId); // Hack to avoid MongoTemplate projection

  Page<PostResponseDto> findAllBy(Pageable pageable);

  List<PostDeletionDto> findAllByUserId(String userId);

  @Update("{'$inc': {'commentsSize': ?1}}")
  long findAndIncrementCommentsSizeById(String postId, long increment);

  void deleteAllByUserId(String userId);
}
