package com.barca.blogmanager.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration("authSecurityConfig")
public class HttpBasicConfig {
  @Bean
  public SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception {
    http.securityMatcher("/auth/**")
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()
            .requestMatchers(HttpMethod.GET, "/auth/token").authenticated()
            .requestMatchers(HttpMethod.DELETE, "/auth/deregister").authenticated())
        .httpBasic(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }
}
