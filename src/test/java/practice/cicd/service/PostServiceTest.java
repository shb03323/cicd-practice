package practice.cicd.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import practice.cicd.controller.dto.PostCreateRequest;
import practice.cicd.entity.Post;
import practice.cicd.entity.PostTag;
import practice.cicd.entity.Tag;
import practice.cicd.repository.PostRepository;
import practice.cicd.repository.PostTagRepository;
import practice.cicd.repository.TagRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DataJpaTest
class PostServiceTest {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostTagRepository postTagRepository;

    private PostService postService;

    @BeforeEach
    void setUp() {
        this.postService = new PostService(postRepository, postTagRepository, tagRepository);
    }

    @DisplayName("저장한다.")
    @Test
    void create_success() {
        // given
        final String title = "안녕하세요.";
        final String contents = "내용입니다.";
        final List<String> tags = List.of("java", "spring");
        final PostCreateRequest request = new PostCreateRequest(title, contents, tags);

        // when
        final Post actual = postService.createPost(request);

        // then
        assertSoftly(softly -> {
            softly.assertThat(actual.getTitle()).isEqualTo(title);
            softly.assertThat(actual.getContents()).isEqualTo( contents);
            softly.assertThat(actual.getPostTags().stream()
                    .map(postTag -> postTag.getTag().getName())
                    .toList())
                    .containsAll(tags);
        });
    }
}
