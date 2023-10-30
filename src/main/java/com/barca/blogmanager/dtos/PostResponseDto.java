package com.barca.blogmanager.dtos;

import java.time.Instant;

public record PostResponseDto(String id, String userId, String userName, String title, String content,
        Instant createdDate, long commentsSize) {
}
