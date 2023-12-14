package techno.hub.backend.repositories;

import techno.hub.backend.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> getBlogByTags_Name(String tagName);
    Optional<Blog> findById(long id);

}