package com.barca.blogmanager.controllers;

import com.barca.blogmanager.dtos.PostCreationDto;
import com.barca.blogmanager.dtos.PostResponseDto;
import com.barca.blogmanager.models.Post;
import com.barca.blogmanager.services.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
  private final PostService postService;

  @GetMapping("/{id}")
  public PostResponseDto getPost(@PathVariable String id) {
    return postService.getPost(id);
  }

  @GetMapping
  public Page<PostResponseDto> getPosts(
      @PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
    return postService.getPosts(pageable);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Void> createPost(@AuthenticationPrincipal Jwt jwt,
      @RequestBody @Valid PostCreationDto postDto) {

    Post post = postService.createPost(jwt.getSubject(), jwt.getClaim("name"), postDto);

    var location = ServletUriComponentsBuilder
        .fromCurrentRequestUri()
        .path("/{postId}")
        .buildAndExpand(post.getId())
        .toUri();

    return ResponseEntity.created(location).build();
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePost(@AuthenticationPrincipal Jwt jwt, @PathVariable String id) {
    postService.deletePost(jwt.getSubject(), id);
  }

}
