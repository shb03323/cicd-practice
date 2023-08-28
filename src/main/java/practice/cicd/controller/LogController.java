package practice.cicd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.cicd.service.LogService;

@RequiredArgsConstructor
@RequestMapping("/logging")
@RestController
public class LogController {

    private final LogService logService;

    @GetMapping
    public ResponseEntity<Void> getLog() {
        logService.logging();
        return ResponseEntity.ok().build();
    }
}
