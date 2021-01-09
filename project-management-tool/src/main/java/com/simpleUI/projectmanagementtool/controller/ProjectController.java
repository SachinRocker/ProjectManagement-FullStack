package com.simpleUI.projectmanagementtool.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simpleUI.projectmanagementtool.domain.Project;
import com.simpleUI.projectmanagementtool.services.FieldsValidationService;
import com.simpleUI.projectmanagementtool.services.ProjectService;

@RestController
@CrossOrigin
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private FieldsValidationService fieldValidation;

	@PostMapping("/project")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult results, Principal principal) {
		
		ResponseEntity<?> errorResponse = fieldValidation.getFiledErrorResponse(results);
		
		if (errorResponse != null)
			return errorResponse;

		projectService.saveOrUpdateProject(project,principal.getName());

		return new ResponseEntity<Project>(project, HttpStatus.CREATED);

	}

	@GetMapping("/project/{projectId}")
	public ResponseEntity<Project> getProjectByID(@PathVariable String projectId, Principal principal) {
		Project project = projectService.findProjectByIdentifier(projectId,principal.getName());

		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@GetMapping("/projects")
	public Iterable<Project> findProjects(Principal principal){
		
		return projectService.findAllProjects(principal.getName());
	}
	
	@DeleteMapping("/project/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId, Principal principal){
		
		projectService.deleteProjectById(projectId, principal.getName());
		
		return new ResponseEntity<String>("Project with id: "+projectId.toUpperCase()+" deleted successfully"
											,HttpStatus.OK);
	}
	
	@PutMapping("/project")
	public ResponseEntity<?> updateProject(@Valid @RequestBody Project project, BindingResult results,Principal principal){
		ResponseEntity<?> errorResponse = fieldValidation.getFiledErrorResponse(results);
		
		if (errorResponse != null)
			return errorResponse;
		
		Project updatedProject = projectService.updateTheProject(project,principal.getName());
		
		return new ResponseEntity<Project>(updatedProject,HttpStatus.OK);
		
	}

}
