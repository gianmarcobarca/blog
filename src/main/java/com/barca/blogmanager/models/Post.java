package com.barca.blogmanager.models;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Value
public class Post {
  // TODO add validation
  @Id
  String id;
  String title;
  String description;
  String content;
  int commentsSize;
}
