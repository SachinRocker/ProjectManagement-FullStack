package com.simpleUI.projectmanagementtool.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Project name required")
	private String projectName;
	
	@NotBlank(message = "Project ID required")
	@Size(min = 4 , max = 5, message = "Size between 4 to 5 characters")
	@Column(unique = true, updatable = false)
	private String projectIdentifier;
	
	@OneToOne( mappedBy = "project",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private Backlog backlog;
	
	@NotBlank(message = "Project description required")
	private String description;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	//@NotBlank(message = "ProjectLead name cannot be blank")
	private String projectLead;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private User user;
	
	public String getProjectLead() {
		return projectLead;
	}


	public void setProjectLead(String projectLead) {
		this.projectLead = projectLead;
	}

	
	
	
	public Backlog getBacklog() {
		return backlog;
	}
	

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}


	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project() {
	
	}

	@PrePersist
	public void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	public void onUpdate() {
		this.updatedAt = new Date();
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifer) {
		this.projectIdentifier = projectIdentifer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	




	

}
