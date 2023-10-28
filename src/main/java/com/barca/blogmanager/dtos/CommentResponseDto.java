package com.barca.blogmanager.dtos;

import java.time.Instant;

public record CommentResponseDto(String id, String postId, String userId, String userName, String content,
    Instant createdDate) {
}
