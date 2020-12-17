package com.simpleUI.projectmanagementtool.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleUI.projectmanagementtool.domain.Backlog;
import com.simpleUI.projectmanagementtool.domain.Project;
import com.simpleUI.projectmanagementtool.exceptionhandler.ProjectIDException;
import com.simpleUI.projectmanagementtool.repository.BacklogRepository;
import com.simpleUI.projectmanagementtool.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	BacklogRepository backlogRepository;

	public Project saveOrUpdateProject(Project project) {
		// write code to find does the exact project id exists in DB
		String projectId = project.getProjectIdentifier().toUpperCase();
		try {

			project.setProjectIdentifier(projectId);
			
			if (project.getId() == null) {
				
				Backlog backlog = new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(projectId);
			
				
			}
			
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
		Long id = project.getId();
		System.out.println(id + " p id:");
		Optional<Project> dbProject = projectRepository.findById(id);

		if (dbProject.isPresent()) {
			projectRepository.save(project);
			return project;
		} else
			throw new ProjectIDException("Project does not exist");
	}
	
	
	
	
	public Project updateTheProject(Project project) {
		String projectIdentifier = project.getProjectIdentifier().toUpperCase();

		Project p = projectRepository.findByprojectIdentifier(projectIdentifier);

		if (p == null)
			throw new ProjectIDException("Project ID: " + projectIdentifier + " does not exist");
		else {
			
			project.setBacklog(backlogRepository.findByProjectIdentifier(projectIdentifier));
			// Optional<Project> dbProject = projectRepository.findById(id);
			project.setId(p.getId());
			projectRepository.save(project);
			return project;

		}
	}
}
