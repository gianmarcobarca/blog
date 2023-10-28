package com.barca.blogmanager.controllers;

import com.barca.blogmanager.dtos.CommentCreationDto;
import com.barca.blogmanager.dtos.CommentResponseDto;
import com.barca.blogmanager.services.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController("commentController")
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @GetMapping
  public Slice<CommentResponseDto> getAllComments(@RequestParam String postId, Pageable pageable) {
    // TODO searching for non-active postIDs returns an empty page
    // TODO test performance between Slice and Page

    return commentService.getAllComments(postId, pageable);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createComment(@AuthenticationPrincipal Jwt jwt, @RequestBody @Valid CommentCreationDto commentDto) {
    commentService.createComment(jwt.getSubject(), jwt.getClaim("name"), commentDto);
  }
}
