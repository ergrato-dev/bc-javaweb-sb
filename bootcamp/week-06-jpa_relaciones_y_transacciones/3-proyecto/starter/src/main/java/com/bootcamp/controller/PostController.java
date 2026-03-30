package com.bootcamp.controller;

import com.bootcamp.dto.*;
import com.bootcamp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * REST Controller for Blog Post operations.
 *
 * TODO:
 *  1. Add @RestController, @RequestMapping("/api/posts") annotations
 *  2. Add constructor with PostService
 *  3. GET /api/posts — Pageable — returns Page<PostSummaryResponse>
 *  4. GET /api/posts/{id} — returns PostDetailResponse (with comments)
 *  5. POST /api/posts — @Valid PostCreateRequest — returns 201 Created
 *  6. POST /api/posts/{id}/comments — @Valid CommentCreateRequest — returns 201 Created
 *  7. PUT /api/posts/{id}/publish — publishes post — returns 200
 *  8. DELETE /api/posts/{id} — returns 204 No Content
 */
public class PostController {
    // TODO: Implement
}
