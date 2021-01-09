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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpleUI.projectmanagementtool.domain.ProjectTask;
import com.simpleUI.projectmanagementtool.services.FieldsValidationService;
import com.simpleUI.projectmanagementtool.services.ProjectTaskService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/backlog")
public class BacklogController {
	
	@Autowired
	private ProjectTaskService projectTaskService;
	
	@Autowired
	private FieldsValidationService fieldValidation;
	
	
	@PostMapping("/{projectIdentifier}")
	public ResponseEntity<?> addProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult results,
			@PathVariable String projectIdentifier,Principal principal) {
		ResponseEntity<?> errorResponse = fieldValidation.getFiledErrorResponse(results);

		if (errorResponse != null)
			return errorResponse;

		ProjectTask task = projectTaskService.addTask(projectIdentifier, projectTask,principal.getName());

		return new ResponseEntity<ProjectTask>(task, HttpStatus.CREATED);
	}

	
	@GetMapping("/{projectIdentifier}")
	public Iterable<ProjectTask> findProjectTasks(@PathVariable String projectIdentifier,Principal principal) {

		return projectTaskService.findBacklogProjectTasks(projectIdentifier,principal.getName());
	}

	
	@GetMapping("/{projectIdentifier}/{projectSequence}")
	public ProjectTask findProjectTask(@PathVariable String projectIdentifier, @PathVariable String projectSequence,Principal principal) {

		return projectTaskService.findProjectTask(projectIdentifier, projectSequence,principal.getName());
	}

	
	
	@PatchMapping("/{projectIdentifier}/{projectSequence}")
	public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult results,
			@PathVariable String projectIdentifier, @PathVariable String projectSequence,Principal principal) {

		ResponseEntity<?> errorResponse = fieldValidation.getFiledErrorResponse(results);

		if (errorResponse != null)
			return errorResponse;
		ProjectTask updatedTask = projectTaskService.updateTask(projectTask, projectIdentifier, projectSequence,principal.getName());

		return new ResponseEntity<ProjectTask>(updatedTask, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{projectIdentifier}/{projectSequence}")
	public ResponseEntity<?> deleteProjectTask(
			@PathVariable String projectIdentifier, @PathVariable String projectSequence,Principal principal) {

		projectTaskService.deleteTask(projectIdentifier,projectSequence,principal.getName());

		return new ResponseEntity<Object>("Project Task deleted", HttpStatus.OK);
	}
	
}
