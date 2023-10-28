package com.barca.blogmanager.services.internal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barca.blogmanager.dtos.UserCreationDto;
import com.barca.blogmanager.models.User;
import com.barca.blogmanager.repositories.PostRepository;
import com.barca.blogmanager.repositories.UserRepository;
import com.barca.blogmanager.security.CustomUserDetails;
import com.barca.blogmanager.security.internal.CustomUserDetailsImpl;
import com.barca.blogmanager.services.UserService;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepository;
  private final PostRepository postRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Nonnull
  public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    // TODO add UsernameNotFoundException to the controller handler

    return new CustomUserDetailsImpl(
        userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found")));
  }

  @Override
  public void createUser(UserCreationDto dto) {
    User copy = User
        .builder()
        .firstName(dto.getFirstName())
        .lastName(dto.getLastName())
        .email(dto.getEmail())
        .password(passwordEncoder.encode(dto.getPassword()))
        .build();

    userRepository.save(copy);
  }

  @Transactional
  @Override
  public void deleteUser(String userId) {

    postRepository.deleteAllByUserId(userId);
    userRepository.deleteById(userId);
  }
}
