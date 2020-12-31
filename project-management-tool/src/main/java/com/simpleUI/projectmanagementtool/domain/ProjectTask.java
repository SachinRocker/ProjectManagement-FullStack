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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javassist.expr.Instanceof;

@Entity
public class ProjectTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(updatable = false, unique = true)
	private String projectSequence;

	@NotBlank(message = "Describe the project summary")
	private String summary;

	private String acceptenceCreteria;
	private String status;
	private Long priority;

	// ManyToOne with Backlog
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "backlog_id", updatable = false, nullable = false)
	@JsonIgnore
	private Backlog backlog;

	@Column(updatable = false)
	public String projectIdentifier;
	private Date createdAt;
	private Date updatedAt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dueDate;

	@PrePersist
	public void startDate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	public void setUpdateDate() {
		this.updatedAt = new Date();
	}

	public ProjectTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Backlog getBacklog() {
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectSequence() {
		return projectSequence;
	}

	public void setProjectSequence(String projectSequence) {
		this.projectSequence = projectSequence;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAcceptenceCreteria() {
		return acceptenceCreteria;
	}

	public void setAcceptenceCreteria(String acceptenceCreteria) {
		this.acceptenceCreteria = acceptenceCreteria;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ProjectTask))
			return false;
		return id!= null && id.equals(((ProjectTask)obj).getId());
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
