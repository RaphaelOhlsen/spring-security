package dev.mocad.springsecuritybasic.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


@Entity(name = "Customer")
public class Customer {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
  @GenericGenerator(name = "native")
  private int id;

  @Column(unique = true)
  private String email;
  private String pwd;
  private String role;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
