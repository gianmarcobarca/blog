package com.barca.blogmanager.controllers;

import com.barca.blogmanager.dtos.PostCreationDto;
import com.barca.blogmanager.dtos.PostResponseDto;
import com.barca.blogmanager.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
  public void createPost(@RequestBody PostCreationDto postDto) {
    postService.createPost(postDto);
  }

  @GetMapping("/{id}")
  public PostResponseDto getPost(@PathVariable String id) {
    return postService.getPost(id);
  }

  @GetMapping
  public Page<PostResponseDto> getPosts(Pageable pageable) {
    return postService.getPosts(pageable);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePost(@PathVariable String id) {postService.deletePost(id);}

}
