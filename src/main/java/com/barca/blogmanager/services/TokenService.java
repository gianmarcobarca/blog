package com.barca.blogmanager.services;

import com.barca.blogmanager.dtos.JwtResponseDto;
import com.barca.blogmanager.security.CustomUserDetails;

public interface TokenService {
  JwtResponseDto createToken(CustomUserDetails userDetails);
}
