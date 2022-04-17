package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.ProjectTask;

import java.util.List;

public interface ProjectTaskService {
public ProjectTask addProject(String projectIdentifier, ProjectTask projectTask);
public List<ProjectTask> listOfProjectTask();
}
