package com.barca.blogmanager.models;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document("comments")
@Value
public class Comment {

  // TODO add validation

  @Id
  String id;
  @Field("post_id")
  String postId;
  @Field("user_name")
  String userName; // TODO think about what happens if user changes name
  @Field("user_id")
  String userId;
  String content;
  @Field("created_date")
  Instant createdDate; // TODO implement timestamp

}
