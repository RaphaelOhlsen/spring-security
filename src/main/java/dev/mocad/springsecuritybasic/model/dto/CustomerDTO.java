package dev.mocad.springsecuritybasic.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CustomerDTO {
  private static final String REGEX_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z\\d])(?=\\S+$)(?!.*(.)\\1).{8,}$"
      ;
  @NotBlank(message = "Email is mandatory")
  @Email(message = "Email should be valid")
  private String email;

  @NotBlank(message = "Password is mandatory")
  @Pattern(regexp = REGEX_PATTERN , message = "A senha deve conter no mínimo 8 caracteres, uma letra maiúscula, uma letra minúscula, um número e um caractere especial, não permitido caracteres repetidos em sequencia")
  private String pwd;

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
}
