package com.barca.blogmanager.services.internal;

import com.barca.blogmanager.dtos.PostCreationDto;
import com.barca.blogmanager.dtos.PostResponseDto;
import com.barca.blogmanager.models.Post;
import com.barca.blogmanager.repositories.PostRepository;
import com.barca.blogmanager.services.PostService;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("postService")
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;

  @Override
  public Post createPost(String userId, String userName, PostCreationDto postDto) {

    Post post = new Post(null, userId, userName, postDto.title(), postDto.content(), null, 0);
    return postRepository.save(post);
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
  public void deletePost(String userId, String postId) {

    // TODO implement logic to delete associated comments

    Optional<Post> result = postRepository.findById(postId);
    Post post = result.orElseThrow();

    if (!(userId.equals(post.getUserId()))) {
      throw new DataIntegrityViolationException("Invalid user ID");
    }

    postRepository.deleteById(post.getId());
  }

  @Override
  public long incrementCommentsSize(String postId) {
    return postRepository.findAndIncrementCommentsSizeById(postId);
  }
}
