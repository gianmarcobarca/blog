package com.barca.blogmanager.security.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.validation.annotation.Validated;

import com.barca.blogmanager.models.User;
import com.barca.blogmanager.security.CustomUserDetails;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

@Value
@Validated
public class CustomUserDetailsImpl implements CustomUserDetails {

  @Getter(AccessLevel.NONE)
  private final transient User user;

  @Override
  @Nonnull
  public String getId() {
    return user.getId();
  }

  @Override
  @Nonnull
  public String getName() {
    return user.getFirstName() + " " + user.getLastName();
  }

  @Override
  @Nonnull
  public String getUsername() {
    return user.getEmail();
  }

  @Override
  @Nonnull
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  @Nonnull
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<String> authorities = user.getAuthorities();
    Set<GrantedAuthority> copy = new HashSet<>();

    for (String authority : authorities) {
      copy.add(new SimpleGrantedAuthority(authority));
    }
    return copy;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
