package com.teetony.springsecurity;

import com.teetony.springsecurity.entities.Role;
import com.teetony.springsecurity.entities.User;
import com.teetony.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
 public class SpringSecurityApplication{

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}


}
