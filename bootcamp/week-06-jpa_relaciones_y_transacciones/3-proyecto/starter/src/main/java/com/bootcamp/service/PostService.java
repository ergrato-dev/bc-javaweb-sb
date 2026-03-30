package com.bootcamp.service;

import com.bootcamp.domain.Comment;
import com.bootcamp.domain.Post;
import com.bootcamp.dto.*;
import com.bootcamp.exception.AuthorNotFoundException;
import com.bootcamp.exception.PostNotFoundException;
import com.bootcamp.repository.AuthorRepository;
import com.bootcamp.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostService(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    /**
     * TODO:
     *  Return all published posts with pagination.
     *  Use postRepository.findByPublishedTrue(pageable) and map to PostSummaryResponse.
     */
    public Page<PostSummaryResponse> findPublished(Pageable pageable) {
        // TODO: Implement
        return Page.empty(pageable);
    }

    /**
     * TODO:
     *  Find post by ID with author and comments loaded (JOIN FETCH).
     *  Use postRepository.findByIdWithAuthorAndComments(id).
     *  Map to PostDetailResponse including list of CommentResponse.
     */
    public PostDetailResponse findById(Long id) {
        // TODO: Implement
        return null;
    }

    /**
     * TODO:
     *  1. Verify author exists (throw AuthorNotFoundException if not)
     *  2. Create new Post with title, content, author
     *  3. Save and map to PostSummaryResponse
     */
    @Transactional
    public PostSummaryResponse create(PostCreateRequest request) {
        // TODO: Implement
        return null;
    }

    /**
     * TODO:
     *  1. Find post by ID (throw PostNotFoundException if not found)
     *  2. Create new Comment with content and authorName
     *  3. Use post.addComment(comment) to maintain bidirectionality
     *  4. Save post (cascade persists comment)
     *  5. Return CommentResponse
     */
    @Transactional
    public CommentResponse addComment(Long postId, CommentCreateRequest request) {
        // TODO: Implement
        return null;
    }

    /**
     * TODO:
     *  1. Find post by ID (throw PostNotFoundException if not found)
     *  2. Update title and content
     *  3. Set published = true
     *  4. Save and return PostSummaryResponse
     */
    @Transactional
    public PostSummaryResponse publish(Long postId) {
        // TODO: Implement
        return null;
    }

    /**
     * TODO:
     *  1. Find post by ID (throw PostNotFoundException if not found)
     *  2. Delete with postRepository.delete(post)
     *  (orphanRemoval will delete associated comments)
     */
    @Transactional
    public void delete(Long postId) {
        // TODO: Implement
    }
}
