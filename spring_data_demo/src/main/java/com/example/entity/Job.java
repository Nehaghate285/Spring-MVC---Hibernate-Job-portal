package com.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Title cant be empty")
	private String title;

	@NotEmpty(message = "Description cant be empty")
	private String description;

	@NotEmpty(message = "Experience cant be empty")
	private String experience;

	// @NotEmpty(message = "Type cant be empty")
	private String type;

	// @NotEmpty(message = "Location cant be empty")
	private String location;

	private String status;
	
	private boolean isDeleted;

	private Date postedOn;
	private Date updatedOn;

	private Long applicationCount;

	@OneToOne
	@JoinColumn(name = "employer_id")
	private Employer employer;

	@Transient
	// which are not saved database but will be present in the scope
	private String postedBy;
	
	@Transient
	private String candidateApplicationStatus;
	

	/** Getters and Setters */
	public Date getPostedOn() {
		return postedOn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Long getApplicationCount() {
		return applicationCount;
	}

	public void setApplicationCount(Long applicationCount) {
		this.applicationCount = applicationCount;
	}

	public String getCandidateApplicationStatus() {
		return candidateApplicationStatus;
	}

	public void setCandidateApplicationStatus(String candidateApplicationStatus) {
		this.candidateApplicationStatus = candidateApplicationStatus;
	}
	
}