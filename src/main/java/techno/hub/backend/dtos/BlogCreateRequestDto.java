package techno.hub.backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.util.List;


@Data
public class BlogCreateRequestDto {
    @JsonProperty("title")
    @NotNull
    private String title;

    @JsonProperty("content")
    @NotNull
    private String content;

    @JsonProperty("tags_ids")
    @Null
    private List<Long> tagsIds;

    @JsonIgnore
    private String authorId;
}
