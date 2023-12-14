package techno.hub.backend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import techno.hub.backend.dtos.BlogCreateRequestDto;
import techno.hub.backend.dtos.BlogDto;
import techno.hub.backend.services.BlogService;
import techno.hub.backend.services.StorageService;

import java.util.List;


@RestController
@RequestMapping("/blogs")
@AllArgsConstructor
@Slf4j
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/all")
    public ResponseEntity<List<BlogDto>> getAllPosts() {
        log.info("Entering getAllPosts method");
        var result = blogService.findAll();
        log.info("Exiting getAllPosts method with result: {}", result);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getPostById(@PathVariable Long id) {
        log.info("Fetching blog with ID: {}", id);
        var result = blogService.getBlogById(id);
        log.info("Result: {}", result);
        return ResponseEntity.ok(result);
    }


    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BlogDto> createBlog(@Valid final BlogCreateRequestDto model, @RequestPart(name = "file") MultipartFile file) {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        var result = blogService.saveBlog(model, file, token.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @PostMapping("/{id}/add-tag")
    public ResponseEntity<Void> addTagToBlog(@PathVariable Long id, @RequestParam List<Long> tagIds) {
        if (tagIds.isEmpty()) throw new IllegalArgumentException("List of tag's id is empty");
        blogService.addTagsToBlog(id, tagIds);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable long id) {
        blogService.deleteBlog(id);
    }

    @GetMapping("/get-by-tag/{tag-name}")
    public ResponseEntity<List<BlogDto>> getBlogsByTagName(@PathVariable("tag-name") String tagName) {
        List<BlogDto> blogs = blogService.getBlogsByTagName(tagName);
        return ResponseEntity.ok(blogs);
    }

    @DeleteMapping("/{blog-id}/remove-tag/{tag-id}")
    public ResponseEntity<String> deleteTagFromBlog(
            @PathVariable("blog-id") long blogId,
            @PathVariable("tag-id") long tagId) {
        blogService.deleteTagFromBlog(blogId, tagId);
        return ResponseEntity.ok("Tag deleted successfully");
    }


}


