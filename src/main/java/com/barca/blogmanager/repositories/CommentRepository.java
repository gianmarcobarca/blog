package com.barca.blogmanager.repositories;

import com.barca.blogmanager.dtos.CommentDeletionDto;
import com.barca.blogmanager.dtos.CommentResponseDto;
import com.barca.blogmanager.models.Comment;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {

  Optional<CommentDeletionDto> findFirstById(String commentId);

  Slice<CommentResponseDto> findAllByPostId(String postId, Pageable pageable);

  void deleteAllByPostId(String postId);

  void deleteAllByPostId(Iterable<String> ids);
}
