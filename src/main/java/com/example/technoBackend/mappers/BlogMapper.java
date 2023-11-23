package com.example.technoBackend.mappers;

import com.example.technoBackend.dtos.BlogDto;
import com.example.technoBackend.entities.Blog;
import com.example.technoBackend.entities.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BlogMapper.class, Tag.class})
public interface BlogMapper extends BaseMapper<Blog, BlogDto> {

}
