package techno.hub.backend.services.impl;

import techno.hub.backend.dtos.CreateTagDto;
import techno.hub.backend.dtos.TagDto;
import techno.hub.backend.entities.Blog;
import techno.hub.backend.entities.Tag;
import techno.hub.backend.mappers.TagMapper;
import techno.hub.backend.repositories.TagRepository;
import techno.hub.backend.services.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public List<TagDto> findAll() {
        return tagMapper.toDTO(tagRepository.findAll());
    }

//    public TagDto getTagById(long id) {
//        var tag = tagRepository.findById(id).orElseThrow(() -> new DbObjectNotFoundException(HttpStatus.NOT_FOUND.toString(), "Tag doesn't exist"));
//        return tagMapper.toDto(tag);
//    }
@Override
@Transactional()
public TagDto getTagById(long id) {
    Tag tag = tagRepository.findById(id)
            .orElseThrow(() -> new EmptyResultDataAccessException("Tag not found with ID: " + id,1  ));

//    return mapToDto(tag);
    return tagMapper.toDto(tag);
}

//    private TagDto mapToDto(Tag tag) {
//        TagDto tagDto = new TagDto();
//        tagDto.setId(tag.getId());
//        tagDto.setName(tag.getName());
//        tagDto.setAuthorId(tag.getAuthorId());
//        // Map blogs if applicable
//        tagDto.setBlogs(BlogDto.fromEntities(tag.getBlogs()));
//        return tagDto;
//    }

    public TagDto createTag(CreateTagDto tag) {
        if(tagRepository.existsByName(tag.getName())) throw new IllegalArgumentException("Field name must be unique");
        var savedEntity = tagRepository.save(tagMapper.toEntity(tag));
        return tagMapper.toDto(savedEntity);
    }

    @Transactional
    public void deleteTag(long id) {
        Tag tagToDelete = tagRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException("Tag not found with ID: " + id,1));

        for (Blog blog : tagToDelete.getBlogs()) {
            blog.getTags().remove(tagToDelete);
        }

        tagRepository.delete(tagToDelete);
    }
    public List<TagDto> getAllTagsWithBlogs() {
        List<Tag> tags = tagRepository.findAll();
//        return tags.stream()
//                .map(TagDto::fromEntityWithBlogs)
//                .collect(Collectors.toList());
        return tagMapper.toDTO(tags);
    }
}

