package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.Backlog;
import jrvsoft.ppmtool.domain.ProjectTask;
import jrvsoft.ppmtool.exception.Exception;
import jrvsoft.ppmtool.repositories.BacklogRepository;
import jrvsoft.ppmtool.repositories.ProjectTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Iterator;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ProjectTaskServiceImpl implements ProjectTaskService {

    private final BacklogRepository backlogRepository;
    private final ProjectTaskRepository projectTaskRepository;
    private final ProjectService projectService;

    @Override
    public ProjectTask addProject(String projectIdentifier, ProjectTask projectTask) {

        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        if (backlog == null) {
            throw new Exception("Project Identifier not found");
        }
        projectTask.setBacklog(backlog);

        Integer backlogSequence = backlog.getPTSequence();

        backlogSequence++;
        backlog.setPTSequence(backlogSequence);

        projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        // set initial priority
        if (ObjectUtils.isEmpty(projectTask.getPriority())) {
            projectTask.setPriority(3);
        }
        // set initial status
        if (ObjectUtils.isEmpty(projectTask.getStatus())) {
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);
    }

    @Override
    public List<ProjectTask> listOfProjectTask(String projectIdentifier) {
        projectService.findByProjectIdentifier(projectIdentifier);
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectIdentifier);
    }

    @Override
    public ProjectTask getProjectTask(String projectIdentifier, String projectSequence) {
        projectService.findByProjectIdentifier(projectIdentifier);
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectSequence);
        if(ObjectUtils.isEmpty(projectTask)){
            throw new Exception("Project Task  with ID '"+ projectSequence+"' does not exist");
        }
        if(!ObjectUtils.nullSafeEquals(projectIdentifier, projectTask.getProjectIdentifier())) {
            throw new Exception("Invalid Project Identifier '"+ projectIdentifier+"'");
        }
        return projectTaskRepository.findByProjectSequence(projectSequence);
    }
}
