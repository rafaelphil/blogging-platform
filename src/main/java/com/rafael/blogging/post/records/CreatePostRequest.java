package com.rafael.blogging.post.records;

import jakarta.validation.constraints.NotBlank;

public record CreatePostRequest(
        @NotBlank String title,
        @NotBlank String content,
        Long authorId
) {
}
