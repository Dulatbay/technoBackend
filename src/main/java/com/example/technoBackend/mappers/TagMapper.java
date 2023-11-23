package com.example.technoBackend.mappers;

import com.example.technoBackend.dtos.TagDto;
import com.example.technoBackend.entities.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = Tag.class)
public interface TagMapper extends BaseMapper<Tag, TagDto> {
}
