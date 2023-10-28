package com.barca.blogmanager.dtos;

import jakarta.validation.constraints.Size;

public record PostCreationDto(@Size(min = 1, max = 60) String title, @Size(min = 1, max = 2000) String content) {
}
