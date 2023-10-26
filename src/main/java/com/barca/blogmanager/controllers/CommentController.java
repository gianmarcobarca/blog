package com.barca.blogmanager.controllers;

import com.barca.blogmanager.dtos.CommentCreationDto;
import com.barca.blogmanager.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("commentController")
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @PostMapping
  public void createComment(@RequestBody CommentCreationDto commentDto) {
    // TODO get user's name and id from authentication parameter

    // Dummy data
    String userId = "123456789";
    String userName = "Gianmarco";

    commentService.createComment(userId, userName, commentDto);
  }
}
