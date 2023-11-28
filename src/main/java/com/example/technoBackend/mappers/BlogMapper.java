package com.example.technoBackend.mappers;

import com.example.technoBackend.dtos.BlogDto;
import com.example.technoBackend.dtos.TagDto;
import com.example.technoBackend.entities.Blog;
import com.example.technoBackend.entities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BlogMapper extends BaseMapper<Blog, BlogDto> {


}

//    @Override
//    default BlogDto toDto(Blog entity) {
//        BlogDto blogDto = new BlogDto();
//        blogDto.setId(entity.getId());
//        blogDto.setAuthorId(entity.getAuthorId());
//        blogDto.setTitle(entity.getTitle());
//        blogDto.setContent(entity.getContent());
//        blogDto.setTags(entity.getTags().stream().map(i -> new TagDto(i.getId(), i.getName())).collect(Collectors.toList()));
//        blogDto.setCreatedAt(entity.getCreatedAt());
//        blogDto.setUpdatedAt(entity.getUpdatedAt());
//        return blogDto;
//    }