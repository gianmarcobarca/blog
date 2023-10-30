package com.barca.blogmanager.repositories;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.barca.blogmanager.configs.MongoConfig;
import com.barca.blogmanager.dtos.PostDeletionDto;
import com.barca.blogmanager.dtos.PostResponseDto;
import com.barca.blogmanager.models.Post;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@DataMongoTest
@Import({ MongoConfig.class })
@TestInstance(Lifecycle.PER_CLASS)
@Transactional
@ActiveProfiles("test")
class PostRepositorySliceTest {

  @Autowired
  PostRepository postRepository;

  @BeforeAll
  void setup() {
    postRepository.deleteAll();
  }

  @Test
  void findFirstById_should_return_1_PostResponseDto_document() {

    postRepository.save(new Post("1", "1", "john doe", "title example 1", "content 1", null, 0));
    postRepository.save(new Post("2", "1", "john doe", "title example 2", "content 2", null, 0));

    Optional<PostResponseDto> result = postRepository.findFirstById("1");

    assertThat(result.get())
        .hasFieldOrPropertyWithValue("id", "1")
        .isInstanceOf(PostResponseDto.class);
  }

  @Test
  void findTopById_should_return_1_PostDeletionDto_document() {

    postRepository.save(new Post("1", "1", "john doe", "title example 1", "content example 1", null, 0));
    postRepository.save(new Post("2", "1", "john doe", "title example 2", "content 2", null, 0));

    Optional<PostDeletionDto> result = postRepository.findTopById("1");

    assertThat(result.get())
        .hasFieldOrPropertyWithValue("id", "1")
        .isInstanceOf(PostDeletionDto.class);

  }

  @Test
  void findAllBy_should_return_all_PostResponseDto_documents() {

    postRepository.save(new Post(null, "1", "john doe", "title example 1", "content example 1", null, 0));
    postRepository.save(new Post(null, "2", "john doe", "title example 2", "content example 2", null, 0));

    Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "createdDate");

    Page<PostResponseDto> page = postRepository.findAllBy(pageable);

    assertThat(page.getContent())
        .hasSize(2)
        .extracting(PostResponseDto::content)
        .containsExactly("content example 2", "content example 1");

    // TODO test for PostResponseDto
  }

  @Test
  void findAllByUserId_should_return_2_PostDeletionDto_documents() {
    postRepository.save(new Post(null, "1", "john doe", "title example 1", "content example 1", null, 0));
    postRepository.save(new Post(null, "1", "john doe", "title example 2", "content example 2", null, 0));
    postRepository.save(new Post(null, "2", "jack doe", "title example 1", "content example 1", null, 0));

    List<PostDeletionDto> result = postRepository.findAllByUserId("1");

    assertThat(result)
        .hasSize(2)
        .extracting(PostDeletionDto::userId)
        .contains("1");

    // TODO test for PostDeletionDto
  }

  @Test
  void findAndIncrementCommentsSizeById_should_increment_commentsSize_by_1() {
    postRepository.save(new Post("1", "1", "john doe", "title example 1", "content example 1", null, 0));

    postRepository.findAndIncrementCommentsSizeById("1", 1);

    Post post = postRepository.findById("1").get();

    assertThat(post.getCommentsSize()).isEqualTo(1);

  }

  @Test
  void findAndIncrementCommentsSizeById_should_decrement_commentsSize_by_1() {
    postRepository.save(new Post("1", "1", "john doe", "title example 1", "content example 1", null, 1));

    postRepository.findAndIncrementCommentsSizeById("1", -1);

    Post post = postRepository.findById("1").get();

    assertThat(post.getCommentsSize()).isZero();

  }

  @Test
  void deleteAllByUserId_should_delete_2_documents() {
    postRepository.save(new Post(null, "1", "john doe", "title example 1", "content example 1", null, 0));
    postRepository.save(new Post(null, "1", "john doe", "title example 2", "content example 2", null, 0));
    postRepository.save(new Post(null, "2", "jack doe", "title example 1", "content example 1", null, 0));

    postRepository.deleteAllByUserId("1");

    List<PostDeletionDto> result = postRepository.findAllByUserId("1");

    assertThat(result).isEmpty();

  }

}
