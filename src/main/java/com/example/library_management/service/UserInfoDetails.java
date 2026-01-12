package com.example.library_management.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.library_management.entity.UserInfo;

public class UserInfoDetails implements UserDetails {
  private String username;
  private String password;
  private List<GrantedAuthority> authorities;

  public UserInfoDetails(UserInfo userInfo) {
    this.username = userInfo.getEmail();
    this.password = userInfo.getPassword();
    this.authorities = List.of(userInfo.getRoles().split(",")).stream()
        .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public String getPassword() {
    return this.password;
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
