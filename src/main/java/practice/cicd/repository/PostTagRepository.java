package practice.cicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.cicd.entity.PostTag;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
}
