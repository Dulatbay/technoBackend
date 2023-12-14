package techno.hub.backend.services;

import org.springframework.web.multipart.MultipartFile;
import techno.hub.backend.dtos.BlogCreateRequestDto;
import techno.hub.backend.dtos.BlogDto;

import java.util.List;

public interface BlogService {
    List<BlogDto> findAll();

    BlogDto getBlogById(long id);


    BlogDto saveBlog(BlogCreateRequestDto blog, MultipartFile file, String authorId);

    void deleteBlog(long id);

    void addTagsToBlog(Long id, List<Long> tagIds);

    List<BlogDto> getBlogsByTagName(String tagName);

    void deleteTagFromBlog(long blogId, long tagId);
}
