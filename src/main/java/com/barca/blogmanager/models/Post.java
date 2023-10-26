package com.barca.blogmanager.models;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document
@Value
public class Post {
  // TODO add validation
  @Id
  String id;
  String title;
  String description;
  String content;
  @Field("created_date")
  Instant createdDate; // TODO implement timestamps
  @Field("comments_size")
  int commentsSize;
}
