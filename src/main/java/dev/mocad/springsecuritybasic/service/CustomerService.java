package dev.mocad.springsecuritybasic.service;

import dev.mocad.springsecuritybasic.model.dto.CustomerDTO;

public interface CustomerService {
  boolean isEmailExist(String email);

  void registerUser(CustomerDTO customerDTO);
}
