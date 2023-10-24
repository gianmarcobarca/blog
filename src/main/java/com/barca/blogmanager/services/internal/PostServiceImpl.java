package com.barca.blogmanager.services.internal;

import com.barca.blogmanager.dtos.PostDto;
import com.barca.blogmanager.models.Post;
import com.barca.blogmanager.repositories.PostRepository;
import com.barca.blogmanager.services.PostService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;

  @Override
  public void createPost(PostDto postDto) {

    Post post = new Post(null, postDto.title(), postDto.description(), postDto.content());
    postRepository.save(post);
  }
}
