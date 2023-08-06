package practice.cicd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import practice.cicd.entity.Post;
import practice.cicd.repository.PostRepository;
import practice.cicd.repository.PostTagRepository;
import practice.cicd.repository.TagRepository;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class PostTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostTagRepository postTagRepository;

    @Test
    void 조인_연산_검증() {

    }
}
