package com.barca.blogmanager.dtos;

import lombok.Value;

public record CommentCreationDto(String postId, String content) {
}
