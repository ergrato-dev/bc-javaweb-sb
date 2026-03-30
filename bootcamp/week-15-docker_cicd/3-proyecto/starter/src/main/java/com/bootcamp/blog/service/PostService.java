package com.bootcamp.blog.service;

import com.bootcamp.blog.domain.Comment;
import com.bootcamp.blog.domain.Post;
import com.bootcamp.blog.domain.PostStatus;
import com.bootcamp.blog.dto.Dtos.*;
import com.bootcamp.blog.exception.PostNotFoundException;
import com.bootcamp.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<PostSummaryResponse> findPublished() {
        return postRepository.findByStatusOrderByCreatedAtDesc(PostStatus.PUBLISHED).stream()
                .map(this::toSummary)
                .toList();
    }

    @Transactional(readOnly = true)
    public PostResponse findById(Long id) {
        return postRepository.findByIdWithComments(id)
                .map(this::toResponse)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    public PostResponse create(PostCreateRequest request) {
        var post = Post.builder()
                .title(request.title())
                .content(request.content())
                .authorEmail(request.authorEmail())
                .status(PostStatus.DRAFT)
                .build();
        return toResponse(postRepository.save(post));
    }

    public PostResponse publish(Long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
        post.publish();
        return toResponse(postRepository.save(post));
    }

    public PostResponse addComment(Long postId, CommentCreateRequest request) {
        var post = postRepository.findByIdWithComments(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        var comment = Comment.builder()
                .content(request.content())
                .authorEmail(request.authorEmail())
                .post(post)
                .build();
        post.getComments().add(comment);
        return toResponse(postRepository.save(post));
    }

    private PostResponse toResponse(Post p) {
        var comments = p.getComments().stream()
                .map(c -> new CommentResponse(c.getId(), c.getContent(),
                        c.getAuthorEmail(), c.getCreatedAt()))
                .toList();
        return new PostResponse(p.getId(), p.getTitle(), p.getContent(),
                p.getAuthorEmail(), p.getStatus(), p.getCreatedAt(),
                p.getPublishedAt(), comments);
    }

    private PostSummaryResponse toSummary(Post p) {
        return new PostSummaryResponse(p.getId(), p.getTitle(), p.getAuthorEmail(),
                p.getStatus(), p.getCreatedAt(), p.getComments().size());
    }
}
