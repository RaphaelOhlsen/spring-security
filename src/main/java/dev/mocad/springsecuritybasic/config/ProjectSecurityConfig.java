package dev.mocad.springsecuritybasic.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

  static class SecurityFilterChainConfiguration {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
     http.authorizeHttpRequests((requests) -> requests
           .requestMatchers("/myAccount","myBalance","myLoans","myCards").authenticated()
           .requestMatchers("/notices","/contact").permitAll())
         .formLogin(Customizer.withDefaults())
         .httpBasic(withDefaults());
     return http.build();
    }

    /**
     *  Configuration to deny all the requests
     */
        /*http.authorizeHttpRequests(requests -> requests.anyRequest().denyAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();*/

    /**
     *  Configuration to permit all the requests
     */
        /*http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();*/
  }
}
