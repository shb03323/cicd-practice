package practice.cicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.cicd.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
