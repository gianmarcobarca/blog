package com.barca.blogmanager.services.internal;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.barca.blogmanager.dtos.JwtResponseDto;
import com.barca.blogmanager.security.CustomUserDetails;
import com.barca.blogmanager.services.TokenService;

import lombok.RequiredArgsConstructor;

@Service("tokenService")
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

  private final JwtEncoder jwtEncoder;

  @Override
  public JwtResponseDto createToken(CustomUserDetails userDetails) {

    Instant now = Instant.now();
    long expiry = 3600L; // 1h

    String scope = userDetails
        .getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));

    JwtClaimsSet claims = JwtClaimsSet
        .builder()
        .subject(userDetails.getId())
        .claim("name", userDetails.getName())
        .claim("email", userDetails.getUsername())
        .claim("scope", scope)
        .issuer("self")
        .issuedAt(now)
        .expiresAt(now.plusSeconds(expiry))
        .build();

    return new JwtResponseDto(jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
  }

}
