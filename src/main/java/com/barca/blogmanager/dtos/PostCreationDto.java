package com.barca.blogmanager.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostCreationDto(@Size(min = 1, max = 60) @NotNull String title,
    @Size(min = 1, max = 2000) @NotNull String content) {
}
