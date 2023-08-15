package practice.cicd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import practice.cicd.controller.dto.PostCreateRequest;
import practice.cicd.entity.Post;
import practice.cicd.service.PostService;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PostCreateRequest request) {
        final Post post = postService.createPost(request);
        final URI redirectUri = UriComponentsBuilder.fromPath("/posts")
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(redirectUri).build();
    }
}
