package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.Backlog;
import jrvsoft.ppmtool.domain.Project;
import jrvsoft.ppmtool.domain.ProjectTask;
import jrvsoft.ppmtool.exception.PriorityException;
import jrvsoft.ppmtool.exception.ProjectIdentifierException;
import jrvsoft.ppmtool.exception.ProjectSequenceException;
import jrvsoft.ppmtool.repositories.BacklogRepository;
import jrvsoft.ppmtool.repositories.ProjectTaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectTaskServiceImpl implements ProjectTaskService {

    private final BacklogRepository backlogRepository;
    private final ProjectTaskRepository projectTaskRepository;
    private final ProjectService projectService;

    @Override
    public ProjectTask addProject(String projectIdentifier, ProjectTask projectTask, String username) {


        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        if (backlog == null) {
            throw new ProjectIdentifierException("Project Identifier not found");
        }

        Project project = projectService.findByProjectIdentifier(projectIdentifier).get();
        if (!ObjectUtils.nullSafeEquals(project.getProjectLeader(), username)) {
            throw new ProjectIdentifierException("Project not found in your account");
        }

        projectTask.setBacklog(backlog);

        Integer backlogSequence = backlog.getPTSequence();

        backlogSequence++;
        backlog.setPTSequence(backlogSequence);

        projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        // set initial priority
        if (ObjectUtils.isEmpty(projectTask.getPriority()) || projectTask.getPriority() == 0) {
            projectTask.setPriority(3);
        }
        // set initial status
        if (ObjectUtils.isEmpty(projectTask.getStatus())) {
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);
    }

    @Override
    public List<ProjectTask> listOfProjectTask(String projectIdentifier, String username) {
        projectService.findByProjectIdentifierAndProjectLeader(projectIdentifier, username);
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectIdentifier);
    }

    @Override
    public ProjectTask getProjectTaskByProjectIdentifierByProjectSequence(String projectIdentifier, String projectSequence, String username) {

        projectService.findByProjectIdentifierAndProjectLeader(projectIdentifier, username);

        ProjectTask projectTask = getProjectTaskByProjectSequence(projectSequence);

        if (!ObjectUtils.nullSafeEquals(projectIdentifier, projectTask.getProjectIdentifier())) {
            throw new ProjectIdentifierException("Invalid Project Identifier '" + projectIdentifier + "'");
        }

        return projectTaskRepository.findByProjectSequence(projectSequence);
    }

    @Override
    public ProjectTask getProjectTaskByProjectSequence(String projectSequence) {
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectSequence);
        if (ObjectUtils.isEmpty(projectTask)) {
            throw new ProjectSequenceException("Project Task  with ID '" + projectSequence + "' does not exist");
        }
        return projectTask;
    }

    @Override
    public ProjectTask updateByProject(ProjectTask updateProjectTask,
                                       String projectIdentifier, String projectSequence
            , String username) {
        // check if project task does exist under projectIdentifier
        getProjectTaskByProjectIdentifierByProjectSequence(projectIdentifier, projectSequence, username);
        if (ObjectUtils.nullSafeEquals(updateProjectTask.getPriority(), 0))
            throw new PriorityException("Invalid Priority");
        // do save
        return projectTaskRepository.save(updateProjectTask);
    }

    @Override
    public void deleteProjectTask(String projectIdentifier, String projectSequence, String username) {

        // check if project task does exist under projectIdentifier
        ProjectTask projectTask = getProjectTaskByProjectIdentifierByProjectSequence(projectIdentifier, projectSequence, username);

        projectTaskRepository.delete(projectTask);
    }
}
