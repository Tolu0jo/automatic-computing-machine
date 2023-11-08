package com.teetony.springsecurity.repository;

import com.teetony.springsecurity.entities.Role;
import com.teetony.springsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
     Optional<User> findByEmail(String email);
     User findByRole(Role role);

}
