package com.simpleUI.projectmanagementtool.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simpleUI.projectmanagementtool.domain.Backlog;
import com.simpleUI.projectmanagementtool.domain.Project;
import com.simpleUI.projectmanagementtool.domain.ProjectTask;
import com.simpleUI.projectmanagementtool.exceptionhandler.BacklogCustomException;
import com.simpleUI.projectmanagementtool.repository.BacklogRepository;
import com.simpleUI.projectmanagementtool.repository.ProjectRepository;
import com.simpleUI.projectmanagementtool.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private ProjectTaskRepository projectTaskRepository;

	@Autowired
	private BacklogRepository backlogRepository;

	@Autowired
	private ProjectRepository projectRepository;

	public ProjectTask addProject(String projectId, ProjectTask projectTask) {
		// Exception handling

		// set PTs to project if project != null and backlog exists.
		String projectIdentifier = projectId.toUpperCase();
		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
		if (backlog == null)
			throw new BacklogCustomException("Project Not Found");
		// set the backlog to PT
		projectTask.setBacklog(backlog);

		Integer ptSequence = backlog.getPTSequence();
		// update the backlog sequence
		ptSequence++;
		backlog.setPTSequence(ptSequence);

		// Project sequence to be like TODO-1 TODO-2 ... TODO-101,TODO-102
		projectTask.setProjectSequence(projectIdentifier + "-" + ptSequence);

		projectTask.setProjectIdentifier(projectIdentifier);

		// Initial priority when priority null
		Long priority = projectTask.getPriority();
		if (priority == null)
			projectTask.setPriority(3L);// set to low priority

		// Initial status when status null
		String status = projectTask.getStatus();
		if (status == "" || status == null)
			projectTask.setStatus("TO_DO");

		return projectTaskRepository.save(projectTask);
	}

	public Iterable<ProjectTask> findBacklogProjectTasks(String projectId) {
		String projectIdentifier = projectId.toUpperCase();

		Project project = projectRepository.findByprojectIdentifier(projectIdentifier);
		if (project == null)
			throw new BacklogCustomException("Project with id: '" + projectId + "' does not exits");
		Iterable<ProjectTask> projectTasks  = projectTaskRepository.findByProjectIdentifier(projectIdentifier);
		//added iterable tasks to the list 
		List<ProjectTask> taskList  = new ArrayList<ProjectTask>();
		//projectTasks.iterator().forEachRemaining(taskList::add);
		projectTasks.forEach(taskList::add);
		if(taskList.size()== 0)
			throw new BacklogCustomException("No project task avaiable");
		return projectTasks;
	}

	public ProjectTask findProjectTask(String projectId, String projectSequence) {

		String projectIdentifier = projectId.toUpperCase();

		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
		if (backlog == null)
			throw new BacklogCustomException("Project with id: '" + projectId + "' does not exits");
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectSequence.toUpperCase());
		if (projectTask == null)
			throw new BacklogCustomException("ProjectTask with sequence: '" + projectSequence + "' does not exits");
		if (!projectIdentifier.equals(projectTask.getProjectIdentifier()))
			throw new BacklogCustomException(
					"Project with id: '" + projectId + "' does not belong to '" + projectSequence + "' project");

		return projectTaskRepository.findByProjectSequence(projectSequence.toUpperCase());

	}

	
	public ProjectTask updateTask(ProjectTask projectTask, String projectIdentifier,
			String projectSequence) {
		ProjectTask task = findProjectTask(projectIdentifier, projectSequence);
		
		if(!projectIdentifier.equals(projectTask.getProjectIdentifier()) ||
			!projectSequence.equals(projectTask.getProjectSequence())) {
				throw new BacklogCustomException("Project mismatch");

		}
		
		
		return  projectTaskRepository.save(projectTask);
		
	
	}

	public void deleteTask(String projectIdentifier, String projectSequence) {
		ProjectTask projectTask  = findProjectTask(projectIdentifier, projectSequence);
		
		projectTaskRepository.delete(projectTask);
		
	}
	
	
}
