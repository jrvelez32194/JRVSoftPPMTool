package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.ProjectTask;

public interface ProjectTaskService {
    ProjectTask addProject(String projectIdentifier, ProjectTask projectTask, String username);

    Iterable<ProjectTask> listOfProjectTask(String projectIdentifier, String username);

    ProjectTask getProjectTaskByProjectIdentifierByProjectSequence(String projectIdentifier, String projectSequence, String username);

    ProjectTask getProjectTaskByProjectSequence(String projectSequence);

    ProjectTask updateByProject(ProjectTask updatedProjectTask, String projectIdentifier, String projectSequence, String username);

    void deleteProjectTask(String projectIdentifier, String projectSequence, String username);
}
