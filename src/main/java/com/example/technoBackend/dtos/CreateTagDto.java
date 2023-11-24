package com.example.technoBackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CreateTagDto {
    private String name;
    @JsonIgnore
    private String authorId;
}
