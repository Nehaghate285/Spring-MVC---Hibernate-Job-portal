package com.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "candidate_id", "job_id" }) })
public class CandidateApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;

	@NotEmpty
	private String applicationStatus;

	@Temporal(TemporalType.TIMESTAMP)
	private Date appliedOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date employerActionOn;



	public CandidateApplication() {
	}

	public CandidateApplication(Candidate candidate, Job job, String applicationStatus, Date appliedOn) {
		this.candidate = candidate;
		this.job = job;
		this.applicationStatus = applicationStatus;
		this.appliedOn = appliedOn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public Date getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(Date appliedOn) {
		this.appliedOn = appliedOn;
	}

	public Date getEmployerActionOn() {
		return employerActionOn;
	}

	public void setEmployerActionOn(Date employerActionOn) {
		this.employerActionOn = employerActionOn;
	}
}