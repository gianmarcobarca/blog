package com.barca.blogmanager.services.internal;

import com.barca.blogmanager.dtos.CommentCreationDto;
import com.barca.blogmanager.dtos.CommentDeletionDto;
import com.barca.blogmanager.dtos.CommentResponseDto;
import com.barca.blogmanager.models.Comment;
import com.barca.blogmanager.repositories.CommentRepository;
import com.barca.blogmanager.repositories.PostRepository;
import com.barca.blogmanager.services.CommentService;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("commentService")
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final PostRepository postRepository;

  @Override
  public Slice<CommentResponseDto> getAllPostComments(String productId, Pageable pageable) {
    return commentRepository.findAllByPostId(productId, pageable);
  }

  @Transactional
  @Override
  public void createComment(String userId, String userName, CommentCreationDto commentDto) {

    long result = postRepository.findAndIncrementCommentsSizeById(commentDto.postId(), 1);

    if (result == 0) {
      throw new NoSuchElementException("Post does not exist");
    }

    Comment comment = new Comment(null, commentDto.postId(), userId, userName, commentDto.content(), null);
    commentRepository.save(comment);
  }

  @Transactional
  @Override
  public void deleteComment(String userId, String commentId) {

    Optional<CommentDeletionDto> result = commentRepository.findFirstById(commentId);
    CommentDeletionDto commentDto = result.orElseThrow();

    if (!(userId.equals(commentDto.userId()))) {
      throw new DataIntegrityViolationException("Invalid user");
    }

    long incrementResult = postRepository.findAndIncrementCommentsSizeById(commentDto.postId(), -1);

    if (incrementResult == 0) {
      throw new NoSuchElementException("Post does not exist");
    }

    commentRepository.deleteById(commentDto.id());
  }
}
