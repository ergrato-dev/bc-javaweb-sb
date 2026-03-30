package com.bootcamp.blog.controller;

import com.bootcamp.blog.dto.Dtos.*;
import com.bootcamp.blog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Tag(name = "Posts", description = "Blog post management")
public class PostController {

  private final PostService postService;

  @GetMapping
  @Operation(summary = "List all published posts")
  public ResponseEntity<List<PostSummaryResponse>> findAll() {
    return ResponseEntity.ok(postService.findPublished());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get post with comments by ID")
  public ResponseEntity<PostResponse> findById(@PathVariable Long id) {
    return ResponseEntity.ok(postService.findById(id));
  }

  @PostMapping
  @Operation(summary = "Create a new post (starts as DRAFT)")
  public ResponseEntity<PostResponse> create(@RequestBody @Valid PostCreateRequest req) {
    return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(req));
  }

  @PostMapping("/{id}/publish")
  @Operation(summary = "Publish a DRAFT post")
  public ResponseEntity<PostResponse> publish(@PathVariable Long id) {
    return ResponseEntity.ok(postService.publish(id));
  }

  @PostMapping("/{id}/comments")
  @Operation(summary = "Add a comment to a post")
  public ResponseEntity<PostResponse> addComment(
      @PathVariable Long id,
      @RequestBody @Valid CommentCreateRequest req) {
    return ResponseEntity.status(HttpStatus.CREATED).body(postService.addComment(id, req));
  }
}
