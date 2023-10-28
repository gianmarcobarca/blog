package com.barca.blogmanager.models;

import lombok.Value;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.Size;

import java.time.Instant;

@Document("posts")
@Value
public class Post {
  // TODO add validation
  @Id
  String id;

  @Field("user_id")
  String userId;

  @Field("user_name")
  @Size(min = 1, max = 100)
  String userName;

  @Size(min = 1, max = 60)
  String title;

  @Size(min = 1, max = 2000)
  String content;

  @Field("created_date")
  @CreatedDate
  Instant createdDate;

  @Field("comments_size")
  int commentsSize;
}
