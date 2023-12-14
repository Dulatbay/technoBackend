package techno.hub.backend.mappers.custom;

import techno.hub.backend.dtos.BlogCreateRequestDto;
import techno.hub.backend.entities.Blog;
import techno.hub.backend.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class BlogCustomMapper {
    private final TagRepository tagRepository;

    public Blog toEntity(BlogCreateRequestDto blogCreateRequestDto) {
        Blog blog = new Blog();
        blog.setTitle(blogCreateRequestDto.getTitle());
        blog.setContent(blogCreateRequestDto.getContent());
        blog.setAuthorId(blogCreateRequestDto.getAuthorId());
        var tags = tagRepository.findAllById(blogCreateRequestDto.getTagsIds());
        if(tags.size() != blogCreateRequestDto.getTagsIds().size())
            throw new IllegalArgumentException("Could not find all specified tags");
        blog.setTags(tags);
        return blog;
    }
}
