package com.barca.blogmanager.controllers;

import com.barca.blogmanager.dtos.PostDto;
import com.barca.blogmanager.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
  private final PostService postService;

  // TODO add authentication/authorization
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createPost(@RequestBody PostDto postDto) {
    postService.createPost(postDto);
  }
}
