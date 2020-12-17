package com.simpleUI.projectmanagementtool.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Backlog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String projectIdentifier;
	
	private Integer PTSequence=0;
	
	//oneToOne with project
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id" , nullable = false)
	@JsonIgnore
	private Project project;
	
	
	
	//oneToMany with project task
	@OneToMany(mappedBy = "backlog",fetch = FetchType.EAGER,cascade = CascadeType.REFRESH, orphanRemoval = true)
	private List<ProjectTask> projectTask;
	
	
	
	

	public Backlog() {
		
	}

	public List<ProjectTask> getProjectTask() {
		return projectTask;
	}

	public void setProjectTask(List<ProjectTask> projectTask) {
		this.projectTask = projectTask;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public Integer getPTSequence() {
		return PTSequence;
	}

	public void setPTSequence(Integer pTSequence) {
		PTSequence = pTSequence;
	}
	
	
	
	


}
