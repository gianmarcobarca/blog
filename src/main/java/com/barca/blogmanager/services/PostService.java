package com.barca.blogmanager.services;

import com.barca.blogmanager.dtos.PostDto;

public interface PostService {
  void createPost(PostDto postDto);
}
