package com.simpleUI.projectmanagementtool.services;

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
			project.setProjectIdentifer(project.getProjectIdentifer().toUpperCase());
			return projectRepository.save(project);
		} catch (RuntimeException exe) {
			throw new ProjectIDException(
					"Project Id: " + project.getProjectIdentifer().toUpperCase() + "already exists");
		}

	}

	public Project findProjectByIdentifier(String projectId) {

		Project project = projectRepository.findByprojectIdentifer(projectId.toUpperCase());
		
		if (project != null)
			return project;
		
		throw new ProjectIDException("Project ID: " + projectId.toUpperCase() + " does not exist");
	}

}
