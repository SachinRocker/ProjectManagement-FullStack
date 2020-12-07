package com.simpleUI.projectmanagementtool.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleUI.projectmanagementtool.domain.Project;
import com.simpleUI.projectmanagementtool.exceptionhandler.ProjectIDException;
import com.simpleUI.projectmanagementtool.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	public Project saveOrUpdateProject(Project project) {
		// write code to find does the exact project id exists in DB
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (RuntimeException exe) {
			throw new ProjectIDException(
					"Project Id: " + project.getProjectIdentifier().toUpperCase() + " already exists");
		}

	}

	public Project findProjectByIdentifier(String projectId) {

		Project project = projectRepository.findByprojectIdentifier(projectId.toUpperCase());

		if (project != null)
			return project;
		else
			throw new ProjectIDException("Project ID: " + projectId.toUpperCase() + " does not exist");
	}

	public Iterable<Project> findAllProjects() {

		return projectRepository.findAll();
	}

	public void deleteProjectById(String projectId) {
		Project project = projectRepository.findByprojectIdentifier(projectId.toUpperCase());
		if (project == null)
			throw new ProjectIDException("Project ID: " + projectId.toUpperCase() + " does not exist");
		else
			projectRepository.delete(project);
	}
	
	public Project updateProject(Project project) {
		int id = project.getId();
		System.out.println(id+" p id:");
		Optional<Project> dbProject = projectRepository.findById(id);
		
		if(dbProject.isPresent()) {
			projectRepository.save(project);
			return project;
		}
		else
			throw new ProjectIDException("Project does not exist");
	}
}
