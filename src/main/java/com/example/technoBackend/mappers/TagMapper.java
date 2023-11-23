package com.example.technoBackend.mappers;

import com.example.technoBackend.dtos.CreateTagDto;
import com.example.technoBackend.dtos.TagDto;
import com.example.technoBackend.entities.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper extends BaseMapper<Tag, TagDto> {
    default Tag toEntity(CreateTagDto createTagDto) {
        Tag tag = new Tag();
        tag.setName(createTagDto.getName());
        tag.setAuthorId(createTagDto.getAuthorId());
        return tag;
    }
}
