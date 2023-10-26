package com.barca.blogmanager.services;

import com.barca.blogmanager.dtos.PostCreationDto;
import com.barca.blogmanager.dtos.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

  void createPost(PostCreationDto postDto);

  PostResponseDto getPost(String id);

  Page<PostResponseDto> getPosts(Pageable pageable);

  void deletePost(String id);

  long incrementCommentsSize(String postId);
}
