package dev.mocad.springsecuritybasic.controller;

import dev.mocad.springsecuritybasic.model.Customer;
import dev.mocad.springsecuritybasic.model.dto.CustomerDTO;
import dev.mocad.springsecuritybasic.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @Autowired
  CustomerRepository customerRepository;

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody CustomerDTO customerDTO) {
    if (customerRepository.isEmailExist(customerDTO.getEmail())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Email already exists");
    }
    Customer savedCustomer = new Customer();
    savedCustomer.setEmail(customerDTO.getEmail());
    savedCustomer.setPwd(customerDTO.getPwd());
    savedCustomer.setRole("user");
    try {
      customerRepository.save(savedCustomer);
      return ResponseEntity.ok("User created successfully");
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("An exception occurred due to"+ ex.getMessage());
    }
  }
}
