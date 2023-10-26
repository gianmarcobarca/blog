package com.barca.blogmanager.services.internal;

import com.barca.blogmanager.dtos.CommentCreationDto;
import com.barca.blogmanager.models.Comment;
import com.barca.blogmanager.repositories.CommentRepository;
import com.barca.blogmanager.services.CommentService;
import com.barca.blogmanager.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("commentService")
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final PostService postService; // TODO make sure PostService will not have to depend on CommentService

  //TODO implement @Transactional
  @Override
  public void createComment(String userId, String userName, CommentCreationDto commentDto) {

    long result = postService.incrementCommentsSize(commentDto.postId());

    if (result == 0) {
      throw new RuntimeException();
    }

    Comment comment = new Comment(null, commentDto.postId(), userName, userId, commentDto.content(), null);
    commentRepository.save(comment);
  }
}
