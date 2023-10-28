package com.barca.blogmanager.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentCreationDto(@NotNull String postId, @NotNull @Size(min = 1, max = 1000) String content) {
}
