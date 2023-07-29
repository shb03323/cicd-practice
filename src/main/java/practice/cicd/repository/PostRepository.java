package practice.cicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.cicd.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
