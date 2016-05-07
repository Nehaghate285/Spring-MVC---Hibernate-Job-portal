package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.entity.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
	Employer findByUsername(@Param("username") String username);
}
