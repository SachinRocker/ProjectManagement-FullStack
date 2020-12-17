package com.simpleUI.projectmanagementtool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simpleUI.projectmanagementtool.domain.ProjectTask;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
	
	Iterable<ProjectTask> findByProjectIdentifier(String projectIdentifier);
	
	
	ProjectTask findByProjectSequence(String projectSequence);

}
