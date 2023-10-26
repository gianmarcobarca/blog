package com.barca.blogmanager.services;

import com.barca.blogmanager.dtos.CommentCreationDto;

public interface CommentService {
  void createComment(String userId, String userName, CommentCreationDto commentDto);
}
