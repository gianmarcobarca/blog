package com.barca.blogmanager.controllers;

import com.barca.blogmanager.dtos.CommentCreationDto;
import com.barca.blogmanager.dtos.CommentResponseDto;
import com.barca.blogmanager.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController("commentController")
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  // TODO test performance between Slice and Page
  @GetMapping
  public Slice<CommentResponseDto> getAllComments(@RequestParam String postId, Pageable pageable) {

    return commentService.getAllComments(postId, pageable);
  }

  @PostMapping
  public void createComment(@RequestBody CommentCreationDto commentDto) {
    // TODO get user's name and id from authentication parameter

    // Dummy data
    String userId = "123456789";
    String userName = "Gianmarco";

    commentService.createComment(userId, userName, commentDto);
  }
}
