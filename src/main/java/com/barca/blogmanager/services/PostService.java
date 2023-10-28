package com.barca.blogmanager.services;

import com.barca.blogmanager.dtos.PostCreationDto;
import com.barca.blogmanager.dtos.PostResponseDto;
import com.barca.blogmanager.models.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

  Post createPost(String userId, String userName, PostCreationDto postDto);

  PostResponseDto getPost(String id);

  Page<PostResponseDto> getPosts(Pageable pageable);

  void deletePost(String userId, String postId);

  long incrementCommentsSize(String postId);
}
