package com.barca.blogmanager.services;

import com.barca.blogmanager.dtos.CommentCreationDto;
import com.barca.blogmanager.dtos.CommentResponseDto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CommentService {

  Slice<CommentResponseDto> getAllPostComments(String productId, Pageable pageable);

  void createComment(String userId, String userName, CommentCreationDto commentDto);

  void deleteComment(String userId, String commentId);

}
