package com.example.technoBackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class BlogCreateRequestDto {
    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("tags_ids")
    private List<Long> tagsIds;
}
