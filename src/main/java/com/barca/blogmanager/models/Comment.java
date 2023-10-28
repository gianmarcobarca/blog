package com.barca.blogmanager.models;

import lombok.Value;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Document("comments")
@Value
public class Comment {

  @Id
  String id;

  @Field("post_id")
  @NotNull
  String postId;

  @Field("user_id")
  @NotNull
  String userId;

  @Field("user_name")
  @NotNull
  String userName; // TODO implement logic to update this field when user updates name

  @Size(min = 1, max = 1500)
  @NotNull
  String content;

  @Field("created_date")
  @CreatedDate
  Instant createdDate;

}
