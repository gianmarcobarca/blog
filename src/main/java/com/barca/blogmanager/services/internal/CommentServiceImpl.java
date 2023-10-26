package com.barca.blogmanager.services.internal;

import com.barca.blogmanager.dtos.CommentCreationDto;
import com.barca.blogmanager.dtos.CommentResponseDto;
import com.barca.blogmanager.models.Comment;
import com.barca.blogmanager.repositories.CommentRepository;
import com.barca.blogmanager.repositories.PostRepository;
import com.barca.blogmanager.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service("commentService")
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  // TODO think about abstracting the use of both repositories
  private final CommentRepository commentRepository;
  private final PostRepository postRepository;

  @Override
  public Slice<CommentResponseDto> getAllComments(String productId, Pageable pageable) {
    return commentRepository.findAllByPostId(productId, pageable);
  }

  // TODO implement @Transactional
  @Override
  public void createComment(String userId, String userName, CommentCreationDto commentDto) {

    long result = postRepository.findAndIncrementCommentsSizeById(commentDto.postId());

    if (result == 0) {
      throw new RuntimeException(); // TODO create a better exception
    }

    Comment comment = new Comment(null, commentDto.postId(), userName, userId, commentDto.content(), null);
    commentRepository.save(comment);
  }
}
