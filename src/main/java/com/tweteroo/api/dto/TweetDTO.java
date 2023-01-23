package com.tweteroo.api.dto;

import jakarta.validation.constraints.NotBlank;

public record TweetDTO(
        @NotBlank String text,
        @NotBlank String username) {
}
