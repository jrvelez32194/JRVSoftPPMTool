package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.ProjectTask;

public interface ProjectTaskService {
    public ProjectTask addProject(String projectIdentifier, ProjectTask projectTask);
    public Iterable<ProjectTask> listOfProjectTask(String projectIdentifier);
    public ProjectTask getProjectTaskByProjectIdentifierByProjectSequence(String projectIdentifier, String projectSequence);
    public ProjectTask getProjectTaskByProjectSequence(String projectSequence);
    public ProjectTask updateByProject(ProjectTask updatedProjectTask, String projectIdentifier, String projectSequence);
}
