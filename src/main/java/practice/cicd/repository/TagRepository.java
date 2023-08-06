package practice.cicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.cicd.entity.Tag;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByName(final String name);
}
