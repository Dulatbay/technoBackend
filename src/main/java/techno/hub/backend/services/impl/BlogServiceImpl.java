package techno.hub.backend.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import techno.hub.backend.dtos.BlogCreateRequestDto;
import techno.hub.backend.dtos.BlogDto;
import techno.hub.backend.entities.Blog;
import techno.hub.backend.exceptions.DbObjectNotFoundException;
import techno.hub.backend.mappers.BlogMapper;
import techno.hub.backend.mappers.custom.BlogCustomMapper;
import techno.hub.backend.repositories.BlogRepository;
import techno.hub.backend.repositories.TagRepository;
import techno.hub.backend.services.BlogService;
import techno.hub.backend.services.StorageService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final TagRepository tagRepository;
    private final BlogCustomMapper blogCustomMapper;
    private final BlogMapper blogMapper;
    private final StorageService storageService;

    @Override
    @Transactional
    public List<BlogDto> findAll() {
        List<Blog> blogs = blogRepository.findAll();
        return blogMapper.toDTO(blogs);
    }

    @Override
    public BlogDto getBlogById(long id) {
        var blog = blogRepository.findById(id)
                .orElseThrow(() -> new DbObjectNotFoundException(HttpStatus.NOT_FOUND.toString(), "Blog doesn't exist"));

        return blogMapper.toDto(blog);
    }


    @Override
    public BlogDto saveBlog(BlogCreateRequestDto blogCreateRequestDto, MultipartFile file, String authorId) {
        blogCreateRequestDto.setAuthorId(authorId);
        if (blogCreateRequestDto.getTagsIds() == null) blogCreateRequestDto.setTagsIds(new ArrayList<>());
        var entityToSave = blogCustomMapper.toEntity(blogCreateRequestDto);
        String fileName = storageService.store(file);
        entityToSave.setImageUrl(fileName);
        log.info("entityToSave: {}", entityToSave);
        var savedEntity = blogRepository.save(entityToSave);
        return blogMapper.toDto(savedEntity);
    }

    @Override
    public void deleteBlog(long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public void addTagsToBlog(Long id, List<Long> tagIds) {
        var blogEntity = blogRepository.findById(id)
                .orElseThrow(
                        () -> new DbObjectNotFoundException(HttpStatus.NOT_FOUND.toString(), "Blog doesn't exist"));
        var tags = tagRepository.findAllById(tagIds);

        if (tags.size() != tagIds.size())
            throw new IllegalArgumentException("Could not find all specified tags");

        if (blogEntity.getTags().stream().anyMatch(tag -> tagIds.contains(tag.getId()))) {
            throw new IllegalArgumentException("Tag already added");
        }

        blogEntity.setTags(tags);
        blogRepository.save(blogEntity);
    }

    @Override
    public List<BlogDto> getBlogsByTagName(String tagName) {
        if (!tagRepository.existsByName(tagName))
            throw new DbObjectNotFoundException(HttpStatus.NOT_FOUND.toString(), "Tag doesn't exits");
        var blogs = blogRepository.getBlogByTags_Name(tagName);
        return blogMapper.toDTO(blogs);
    }

    @Override
    public void deleteTagFromBlog(long blogId, long tagId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new DbObjectNotFoundException(HttpStatus.NOT_FOUND.toString(), "Blog not found"));
        blog.getTags().removeIf(tag -> tag.getId() == (tagId));
        blogRepository.save(blog);
    }

}