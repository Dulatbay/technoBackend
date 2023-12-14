package techno.hub.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
    private Long id;
    private String name;
//    private String authorId;
//    private List<BlogDto> blogs;

//    public static TagDto fromEntity(Tag tag) {
//        TagDto tagDto = new TagDto();
//        tagDto.setId(tag.getId());
//        tagDto.setName(tag.getName());
//        tagDto.setAuthorId(tag.getAuthorId());
//        return tagDto;
//    }
//
//
//    public void setBlogs(List<BlogDto> blogs) {
//        this.blogs = blogs;
//    }
//    public List<BlogDto> getBlogs() {
//        return blogs;
//    }
//
//    public static TagDto fromEntityWithBlogs(Tag tag) {
//        TagDto tagDto = new TagDto();
//        tagDto.setId(tag.getId());
//        tagDto.setName(tag.getName());
//        tagDto.setAuthorId(tag.getAuthorId());
//        tagDto.setBlogs(BlogDto.fromEntities(tag.getBlogs()));
//        return tagDto;
//    }
//    public static List<TagDto> fromEntities(List<Tag> tags) {
//        return tags.stream().map(TagDto::fromEntity).collect(Collectors.toList());
//    }

}
