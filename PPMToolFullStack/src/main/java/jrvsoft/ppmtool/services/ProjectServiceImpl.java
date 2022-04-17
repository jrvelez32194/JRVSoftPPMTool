package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.Backlog;
import jrvsoft.ppmtool.domain.Project;
import jrvsoft.ppmtool.exception.Exception;
import jrvsoft.ppmtool.repositories.BacklogRepository;
import jrvsoft.ppmtool.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final BacklogRepository backlogRepository;

    @Override
    public Project saveOrUpdate(Project project) {
        String identifier = project.getProjectIdentifier().toUpperCase();
        project.setProjectIdentifier(identifier);
        if (project.getId() == null) {

            if(projectRepository.findByProjectIdentifier(project.getProjectIdentifier()).isPresent()) {
                throw new Exception("Already Exist");
            }

            Backlog backlog = new Backlog();
            project.setBacklog(backlog);
            backlog.setProject(project);
            backlog.setProjectIdentifier(identifier);
        } else {
            project.setBacklog(backlogRepository.findByProjectIdentifier(identifier));
        }

        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> findByProjectIdentifier(String projectIdentifier) {
        Optional<Project> project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if (!project.isPresent()) {
            throw new Exception("Project Identifier " + projectIdentifier + " is not found");
        }
        return project;
    }

    @Override
    public List<Project> listOfProject() {
        return projectRepository.findAll();
    }

    @Override
    public void deleteProject(String projectIdentifier) {
        Project project = findByProjectIdentifier(projectIdentifier).get();
        projectRepository.delete(project);
    }
}
