package com.barca.blogmanager.repositories;

import com.barca.blogmanager.dtos.CommentResponseDto;
import com.barca.blogmanager.models.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {

  Slice<CommentResponseDto> findAllByPostId(String postId, Pageable pageable);

  void deleteAllByPostId(String postId);

  void deleteAllByPostId(Iterable<String> ids);
}
