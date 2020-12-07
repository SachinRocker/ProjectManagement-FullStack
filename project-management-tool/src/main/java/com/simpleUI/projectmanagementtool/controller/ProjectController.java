package com.simpleUI.projectmanagementtool.controller;

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
	FieldsValidationService fieldValidation;

	@PostMapping("/project")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult results) {
		
		ResponseEntity<?> errorResponse = fieldValidation.getFiledErrorResponse(results);
		
		if (errorResponse != null)
			return errorResponse;

		projectService.saveOrUpdateProject(project);

		return new ResponseEntity<Project>(project, HttpStatus.CREATED);

	}

	@GetMapping("/project/{projectId}")
	public ResponseEntity<Project> getProjectByID(@PathVariable String projectId) {
		Project project = projectService.findProjectByIdentifier(projectId);

		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@GetMapping("/projects")
	public Iterable<Project> findProjects(){
		
		return projectService.findAllProjects();
	}
	
	@DeleteMapping("/project/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId){
		
		projectService.deleteProjectById(projectId);
		
		return new ResponseEntity<String>("Project with id: "+projectId.toUpperCase()+" deleted successfully"
											,HttpStatus.OK);
	}
	
	@PutMapping("/project")
	public ResponseEntity<?> updateProject(@RequestBody Project project){
		Project updatedProject = projectService.updateProject(project);
		
		return new ResponseEntity<Project>(updatedProject,HttpStatus.OK);
		
	}

}
