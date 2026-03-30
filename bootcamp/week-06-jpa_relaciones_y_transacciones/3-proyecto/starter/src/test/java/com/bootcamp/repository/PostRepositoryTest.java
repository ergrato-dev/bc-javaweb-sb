package com.bootcamp.repository;

import com.bootcamp.domain.Author;
import com.bootcamp.domain.Comment;
import com.bootcamp.domain.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PostRepositoryTest {

  @Autowired
  TestEntityManager em;
  @Autowired
  PostRepository postRepository;
  @Autowired
  AuthorRepository authorRepository;

  private Author alice;
  private Post publishedPost;
  private Post draftPost;

  @BeforeEach
  void setUp() {
    alice = em.persist(new Author("Alice", "alice@blog.com", "Writer"));
    publishedPost = new Post("Published Post", "Content", alice);
    publishedPost.setPublished(true);
    em.persist(publishedPost);
    draftPost = em.persist(new Post("Draft Post", "Draft content", alice));
    var comment = new Comment("Nice post!", "reader1");
    publishedPost.addComment(comment);
    em.persist(comment);
    em.flush();
  }

  @Test
  void findByPublishedTrue_shouldReturnOnlyPublishedPosts() {
    var page = postRepository.findByPublishedTrue(PageRequest.of(0, 10));
    assertThat(page.getContent()).hasSize(1);
    assertThat(page.getContent().get(0).getTitle()).isEqualTo("Published Post");
  }

  @Test
  void findByIdWithAuthor_shouldLoadAuthorInSameQuery() {
    var post = postRepository.findByIdWithAuthor(publishedPost.getId());
    assertThat(post).isPresent();
    assertThat(post.get().getAuthor().getName()).isEqualTo("Alice");
  }

  @Test
  void findByIdWithAuthorAndComments_shouldLoadAllRelations() {
    var post = postRepository.findByIdWithAuthorAndComments(publishedPost.getId());
    assertThat(post).isPresent();
    assertThat(post.get().getComments()).hasSize(1);
    assertThat(post.get().getComments().get(0).getContent()).isEqualTo("Nice post!");
  }

  @Test
  void authorRepository_shouldFindByEmail() {
    var found = authorRepository.findByEmail("alice@blog.com");
    assertThat(found).isPresent();
    assertThat(found.get().getName()).isEqualTo("Alice");
  }

  @Test
  void deletePost_shouldCascadeToComments() {
    var postId = publishedPost.getId();
    postRepository.deleteById(postId);
    em.flush();
    assertThat(postRepository.findById(postId)).isEmpty();
  }
}
