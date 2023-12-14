package techno.hub.backend.controllers;

import lombok.extern.slf4j.Slf4j;
import techno.hub.backend.dtos.CreateTagDto;
import techno.hub.backend.dtos.TagDto;
import techno.hub.backend.services.TagService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tags")
@AllArgsConstructor
@Slf4j
public class TagController {

    private final TagService tagService;

    @GetMapping("/all")
    public ResponseEntity<List<TagDto>> getAllTagsWithBlogs() {
        List<TagDto> tags = tagService.getAllTagsWithBlogs();
        return ResponseEntity.ok(tags);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTagById(@PathVariable long id) {
        log.info("Fetching tag with ID: {}", id);
        var result = tagService.getTagById(id);
        log.info("Result: {}", result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<TagDto> createTag(@RequestBody CreateTagDto tag) {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        tag.setAuthorId(token.getName());
        var createdTag = tagService.createTag(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTag);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTag(@PathVariable long id) {
        tagService.deleteTag(id);
    }


}

