package com.example.technoBackend.dtos;

import com.example.technoBackend.entities.Blog;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("author_id")
    private String authorId;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("tags")
    private List<TagDto> tags;
//    public static List<BlogDto> fromEntities(List<Blog> blogs) {
//        return blogs.stream()
//                .map(BlogDto::fromEntity)
//                .collect(Collectors.toList());
//    }


//    public static BlogDto fromEntity(Blog blog) {
//        BlogDto blogDto = new BlogDto();
//        blogDto.setId(blog.getId());
//        blogDto.setTitle(blog.getTitle());
//        blogDto.setContent(blog.getContent());
//        blogDto.setAuthorId(blog.getAuthorId());
//        blogDto.setCreatedAt(blog.getCreatedAt());
//        blogDto.setUpdatedAt(blog.getUpdatedAt());
//        blogDto.setTags(TagDto.fromEntities(blog.getTags()));
//        return blogDto;
//    }
}
