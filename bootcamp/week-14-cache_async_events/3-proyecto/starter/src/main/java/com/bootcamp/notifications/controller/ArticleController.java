package com.bootcamp.notifications.controller;

import com.bootcamp.notifications.domain.ArticleStatus;
import com.bootcamp.notifications.dto.Dtos.*;
import com.bootcamp.notifications.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
@Tag(name = "Articles", description = "Article management API")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    @Operation(summary = "List all articles")
    public ResponseEntity<List<ArticleResponse>> findAll(
            @RequestParam(required = false) ArticleStatus status) {
        var articles = status != null
                ? articleService.findByStatus(status)
                : articleService.findAll();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get article by ID")
    @ApiResponse(responseCode = "200", description = "Article found")
    @ApiResponse(responseCode = "404", description = "Article not found")
    public ResponseEntity<ArticleResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new article (starts as DRAFT)")
    @ApiResponse(responseCode = "201", description = "Article created")
    public ResponseEntity<ArticleResponse> create(@RequestBody @Valid ArticleCreateRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.create(req));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update article content")
    public ResponseEntity<ArticleResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid ArticleUpdateRequest req) {
        return ResponseEntity.ok(articleService.update(id, req));
    }

    @PostMapping("/{id}/publish")
    @Operation(summary = "Publish a DRAFT article")
    @ApiResponse(responseCode = "200", description = "Article published")
    @ApiResponse(responseCode = "409", description = "Article is not in DRAFT status")
    public ResponseEntity<ArticleResponse> publish(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.publish(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an article")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        articleService.delete(id);
    }
}
