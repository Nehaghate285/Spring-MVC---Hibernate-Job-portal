package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

	List<Job> findByEmployerId(@Param("employerId") Long employerId);

	@Query(nativeQuery = true, value = "select * from job where employer_id = :employerId and is_deleted = :deleted")
	List<Job> findActiveJobsByEmployerId(@Param("employerId") Long employerId, @Param("deleted") Boolean deleted);

	List<Job> findByIsDeleted(@Param("isDeleted") boolean isDeleted);
}