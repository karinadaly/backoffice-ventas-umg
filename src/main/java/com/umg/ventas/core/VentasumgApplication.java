package com.umg.ventas.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class VentasumgApplication {

  public static void main(String[] args) {
    String password = "123";
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(password);
    System.out.println("Password: " + hashedPassword);
    SpringApplication.run(VentasumgApplication.class, args);
  }
}
