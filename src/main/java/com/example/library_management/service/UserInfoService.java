package com.example.library_management.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.library_management.entity.UserInfo;
import com.example.library_management.repository.UserInfoRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserInfoService implements UserDetailsService {
  private final UserInfoRepository repository;
  private final PasswordEncoder encoder;

  public UserInfoService(UserInfoRepository repository, PasswordEncoder encoder) {
    this.repository = repository;
    this.encoder = encoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserInfo> userInfo = repository.findByEmail(username);

    if (userInfo.isEmpty()) {
      UserInfo user = userInfo.get();
      return new User(user.getEmail(), user.getPassword(), Arrays.stream(user.getRoles().split(","))
          .map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
    UserInfo user = userInfo.get();
    return new User(user.getEmail(), user.getPassword(), List.of(user.getRoles().split(","))
        .stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
  }

  public String addUser(UserInfo userInfo) {
    System.out.println("userInfo" + userInfo);
    userInfo.setPassword(encoder.encode(userInfo.getPassword()));
    repository.save(userInfo);
    return "User added successfully!";
  }
}
