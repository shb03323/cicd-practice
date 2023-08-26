package practice.cicd.controller.dto;

import practice.cicd.entity.Post;

import java.util.List;

public record PostResponse(String title, String contents, List<String> tags) {

    public static PostResponse from(final Post post) {
        return new PostResponse(
                post.getTitle(),
                post.getContents(),
                post.getPostTags().stream()
                        .map(postTag -> postTag.getTag().getName())
                        .toList()
        );
    }
}
