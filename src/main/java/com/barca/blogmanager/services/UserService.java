package com.barca.blogmanager.services;

import com.barca.blogmanager.dtos.UserCreationDto;

public interface UserService {
  void createUser(UserCreationDto dto);

  void deleteUser(String userId);
}
