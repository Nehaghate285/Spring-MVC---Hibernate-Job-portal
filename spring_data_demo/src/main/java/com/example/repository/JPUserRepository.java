package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.entity.JPUser;

public interface JPUserRepository extends JpaRepository<JPUser, Long> {

	JPUser findByUsernameIgnoreCase(@Param("username") String username);

}