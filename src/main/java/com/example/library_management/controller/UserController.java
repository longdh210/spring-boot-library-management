package com.example.library_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.library_management.entity.AuthRequest;
import com.example.library_management.entity.UserInfo;
import com.example.library_management.service.JwtService;
import com.example.library_management.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
  @Autowired
  private UserInfoService service;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @GetMapping("/welcome")
  public String welcome() {
    return "Welcome to open endpoint!";
  }

  @PostMapping("/addNewUser")
  public String addNewUser(@RequestBody UserInfo userInfo) {
    System.out.println("userInfo in controller: " + userInfo);
    return service.addUser(userInfo);
  }

  @PostMapping("/generateToken")
  public String authenticationAndGetToken(@RequestBody AuthRequest authRequest) {
    Authentication authentication =
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            authRequest.getUsername(), authRequest.getPassword()));

    if (authentication.isAuthenticated()) {
      return jwtService.generateToken(authRequest.getUsername());
    } else {
      throw new UsernameNotFoundException("Invalid user request!");
    }
  }

  @GetMapping("/user/userProfile")
  public String getUserProfile() {
    return "User Profile Data";
  }

}
