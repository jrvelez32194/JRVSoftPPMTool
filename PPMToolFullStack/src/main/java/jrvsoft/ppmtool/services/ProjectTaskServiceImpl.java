package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.Backlog;
import jrvsoft.ppmtool.domain.ProjectTask;
import jrvsoft.ppmtool.repositories.BacklogRepository;
import jrvsoft.ppmtool.repositories.ProjectTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ProjectTaskServiceImpl implements ProjectTaskService {

    private final BacklogRepository backlogRepository;
    private final ProjectTaskRepository projectTaskRepository;

    @Override
    public ProjectTask addProject(String projectIdentifier, ProjectTask projectTask) {

        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
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
    public List<ProjectTask> listOfProjectTask() {
        return projectTaskRepository.findAll();
    }
}
