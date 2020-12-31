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

	private Integer PTSequence = 0;

	// oneToOne with project
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = false)
	@JsonIgnore
	private Project project;

	// oneToMany with project task
	@OneToMany(mappedBy = "backlog", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProjectTask> projectTasks;

	public Backlog() {

	}

	public List<ProjectTask> getProjectTask() {
		return projectTasks;
	}

	public void setProjectTask(List<ProjectTask> projectTask) {
		this.projectTasks = projectTask;
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

	// methods to synchronize the relation between the mappings
	public void addTaskToBacklog(ProjectTask task) {
		projectTasks.add(task);
		task.setBacklog(this);
	}

	public void removeTaskFromBacklog(ProjectTask task) {
		projectTasks.remove(task);
		task.setBacklog(null);
	}

}
