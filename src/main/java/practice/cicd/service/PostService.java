package practice.cicd.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.cicd.controller.dto.PostCreateRequest;
import practice.cicd.entity.Post;
import practice.cicd.entity.PostTag;
import practice.cicd.entity.Tag;
import practice.cicd.repository.PostRepository;
import practice.cicd.repository.PostTagRepository;
import practice.cicd.repository.TagRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;
    private final TagRepository tagRepository;

    @Transactional
    public Post createPost(PostCreateRequest request) {
        final Post post = new Post(request.title(), request.contents());

        for (String tagName : request.tags()) {
            final Optional<Tag> maybeTagName = tagRepository.findByName(tagName);
            maybeTagName.ifPresentOrElse(
                    tag -> post.addPostTag(new PostTag(post, tag)),
                    () -> {
                        final Tag tag = tagRepository.save(new Tag(tagName));
                        post.addPostTag(new PostTag(post, tag));
                    }
            );
        }
        return postRepository.save(post);
    }

    public List<Post> readAll() {
        return postRepository.findAll();
    }
}
