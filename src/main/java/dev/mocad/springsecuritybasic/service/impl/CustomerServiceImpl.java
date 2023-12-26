package dev.mocad.springsecuritybasic.service.impl;

import dev.mocad.springsecuritybasic.model.Customer;
import dev.mocad.springsecuritybasic.model.dto.CustomerDTO;
import dev.mocad.springsecuritybasic.repository.CustomerRepository;
import dev.mocad.springsecuritybasic.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public boolean isEmailExist(String email) {
    return customerRepository.isEmailExist(email);
  }

  @Override
  public void registerUser(CustomerDTO customerDTO) {
    if (!isEmailExist(customerDTO.getEmail())) {
      Customer newCustomer = new Customer();
      newCustomer.setEmail(customerDTO.getEmail());
      newCustomer.setPwd(passwordEncoder.encode(customerDTO.getPwd()));
      newCustomer.setRole("user");

      customerRepository.save(newCustomer);
    }
  }
}
