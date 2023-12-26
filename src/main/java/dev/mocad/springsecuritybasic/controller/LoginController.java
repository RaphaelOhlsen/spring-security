package dev.mocad.springsecuritybasic.controller;

import dev.mocad.springsecuritybasic.model.dto.CustomerDTO;
import dev.mocad.springsecuritybasic.service.CustomerService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @Autowired
  private CustomerService customerService;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody @Valid CustomerDTO customerDTO) {
    if (customerService.isEmailExist(customerDTO.getEmail())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Email already exists");
    }

    try {
      customerService.registerUser(customerDTO);
      return ResponseEntity.ok("User created successfully");
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("An exception occurred due to"+ ex.getMessage());
    }
  }
}
