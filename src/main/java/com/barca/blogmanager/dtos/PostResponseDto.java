package com.barca.blogmanager.dtos;

public record PostResponseDto(String id, String userId, String userName, String title, String content,
    long commentsSize) {
}
