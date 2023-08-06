package practice.cicd.controller.dto;

import java.util.List;

public record PostCreateRequest(String title, String contents, List<String> tags) {
}
