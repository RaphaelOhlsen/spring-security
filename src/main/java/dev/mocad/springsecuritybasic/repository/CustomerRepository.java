package dev.mocad.springsecuritybasic.repository;

import dev.mocad.springsecuritybasic.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

  List<Customer> findByEmail(String email);

  @Query("select count(1) > 0 from Customer where email = ?1")
  Boolean isEmailExist(String email);
}
