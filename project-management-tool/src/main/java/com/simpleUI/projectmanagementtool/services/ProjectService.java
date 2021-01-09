package com.simpleUI.projectmanagementtool.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleUI.projectmanagementtool.domain.Backlog;
import com.simpleUI.projectmanagementtool.domain.Project;
import com.simpleUI.projectmanagementtool.domain.User;
import com.simpleUI.projectmanagementtool.exceptionhandler.ProjectIDException;
import com.simpleUI.projectmanagementtool.exceptionhandler.ProjectNotFoundException;
import com.simpleUI.projectmanagementtool.repository.BacklogRepository;
import com.simpleUI.projectmanagementtool.repository.ProjectRepository;
import com.simpleUI.projectmanagementtool.repository.UserRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	BacklogRepository backlogRepository;
	
	@Autowired
	UserRepository userRepository;

	public Project saveOrUpdateProject(Project project,String username) {
		// write code to find does the exact project id exists in DB
		String projectId = project.getProjectIdentifier().toUpperCase();
		try {
			User user = userRepository.findByUsername(username);
			user.addUserProject(project);
			project.setProjectLead(user.getUsername());
			project.setProjectIdentifier(projectId);
			
			
			if (project.getId() == null) {
				
				Backlog backlog  = new Backlog();
				backlog.setProject(project);
				backlog.setProjectIdentifier(projectId);
				project.setBacklog(backlog);
				
			}
			
			return projectRepository.save(project);
		} catch (RuntimeException exe) {
			throw new ProjectIDException(
					"Project Id: " + project.getProjectIdentifier().toUpperCase() + " already exists");
		}

	}

	public Project findProjectByIdentifier(String projectId,String username) {

		Project project = projectRepository.findByprojectIdentifier(projectId.toUpperCase());

		if (project == null) {
			throw new ProjectIDException("Project ID: " + projectId.toUpperCase() + " does not exist");
		}
		if(!project.getProjectLead().equals(username))
			throw new ProjectNotFoundException("No project with Id '"+projectId+"' associated to this account");
			
		
		return project;
		
			
	}

	public Iterable<Project> findAllProjects(String username) {

		return projectRepository.findAllByProjectLead(username);
	}

	public void deleteProjectById(String projectId, String username) {
		Project project = projectRepository.findByprojectIdentifier(projectId.toUpperCase());
		if (project == null)
			throw new ProjectIDException("Project ID: " + projectId.toUpperCase() + " does not exist");
		if(!project.getProjectLead().equals(username))
			throw new ProjectNotFoundException("No project with Id '"+projectId+"' associated to this account");
		else {
			System.out.println("project:: "+project);
			projectRepository.delete(project);
		}
			
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
	
	
	
	
	public Project updateTheProject(Project project, String username) {
		String projectIdentifier = project.getProjectIdentifier().toUpperCase();

		Project p = findProjectByIdentifier(projectIdentifier, username);
		
			User user = userRepository.findByUsername(username);
			user.addUserProject(project);
			project.setProjectLead(user.getUsername());
			project.setBacklog(backlogRepository.findByProjectIdentifier(projectIdentifier));
			project.setId(p.getId());
			projectRepository.save(project);
			return project;
		
	}
}
