package techno.hub.backend.mappers;

import techno.hub.backend.dtos.BlogDto;
import techno.hub.backend.entities.Blog;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BlogMapper extends BaseMapper<Blog, BlogDto> {

}

