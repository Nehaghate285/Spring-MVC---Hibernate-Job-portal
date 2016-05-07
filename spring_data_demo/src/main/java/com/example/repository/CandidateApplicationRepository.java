package com.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.CandidateApplication;

public interface CandidateApplicationRepository extends JpaRepository<CandidateApplication, Long> {
	List<CandidateApplication> findByJobId(@Param("jobId") Long jobId);

	@Query(nativeQuery = true, value = "select candidate_id from candidate_application where job_id = :jobId")
	List<BigInteger> findCandidateIdsByJobId(@Param("jobId") Long jobId);

	CandidateApplication findByJobIdAndCandidateId(@Param("jobId") Long jobId, @Param("candidateId") Long candidateId);

	
	List<CandidateApplication> findByCandidateId(@Param("candidateId") Long candidateId);
}