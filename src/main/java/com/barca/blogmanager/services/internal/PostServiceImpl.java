package com.barca.blogmanager.services.internal;

import com.barca.blogmanager.dtos.PostCreationDto;
import com.barca.blogmanager.dtos.PostResponseDto;
import com.barca.blogmanager.models.Post;
import com.barca.blogmanager.repositories.PostRepository;
import com.barca.blogmanager.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("postService")
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;

  @Override
  public void createPost(PostCreationDto postDto) {

    Post post = new Post(null, postDto.title(), postDto.description(), postDto.content(), 0);
    postRepository.save(post);
  }

  @Override
  public PostResponseDto getPost(String id) {
    return postRepository.findFirstById(id).orElseThrow();
  }

  @Override
  public Page<PostResponseDto> getPosts(Pageable pageable) {
    return postRepository.findAllBy(pageable);
  }

  @Override
  public void deletePost(String id) {

    Optional<Post> result = postRepository.findById(id);
    result.orElseThrow();

    postRepository.deleteById(id);
  }

  @Override
  public long incrementCommentsSize(String postId) {
    return postRepository.findAndIncrementCommentsSizeById(postId);
  }
}
