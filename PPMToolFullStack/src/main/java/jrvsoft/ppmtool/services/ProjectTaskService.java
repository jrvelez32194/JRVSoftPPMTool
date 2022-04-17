package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.ProjectTask;

public interface ProjectTaskService {
     ProjectTask addProject(String projectIdentifier, ProjectTask projectTask);
     Iterable<ProjectTask> listOfProjectTask(String projectIdentifier);
     ProjectTask getProjectTaskByProjectIdentifierByProjectSequence(String projectIdentifier, String projectSequence);
     ProjectTask getProjectTaskByProjectSequence(String projectSequence);
     ProjectTask updateByProject(ProjectTask updatedProjectTask, String projectIdentifier, String projectSequence);
     void deleteProjectTask(String projectIdentifier, String projectSequence);
}
