package com.barca.blogmanager.dtos;

import java.time.Instant;

public record CommentResponseDto(String id, String postId, String userName, String userId, String content,
                                 Instant createdDate){
  // TODO add validation
}
