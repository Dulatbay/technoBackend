package techno.hub.backend.mappers;

import techno.hub.backend.dtos.CreateTagDto;
import techno.hub.backend.dtos.TagDto;
import techno.hub.backend.entities.Tag;
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
