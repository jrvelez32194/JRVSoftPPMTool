package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.Backlog;
import jrvsoft.ppmtool.domain.Project;
import jrvsoft.ppmtool.domain.User;
import jrvsoft.ppmtool.exception.ProjectIdentifierException;
import jrvsoft.ppmtool.repositories.BacklogRepository;
import jrvsoft.ppmtool.repositories.ProjectRepository;
import jrvsoft.ppmtool.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final BacklogRepository backlogRepository;
    private final UserService userService;

    @Override
    public Project saveOrUpdate(Project project, String username) {

        // find user by username
       User user =  userService.findByUsername(username);

        String identifier = project.getProjectIdentifier().toUpperCase();
        project.setProjectIdentifier(identifier);

        // set user
        project.setUser(user);
        project.setProjectLeader(user.getUsername());

        if (project.getId() == null) {

            if(projectRepository.findByProjectIdentifierAndProjectLeader(project.getProjectIdentifier(), username).isPresent()) {
                throw new ProjectIdentifierException("Already Exist");
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
    public Optional<Project> findByProjectIdentifierAndProjectLeader(String projectIdentifier, String projectLeader) {
        Optional<Project> project = projectRepository.findByProjectIdentifierAndProjectLeader(projectIdentifier.toUpperCase(), projectLeader);
        if (!project.isPresent()) {
            throw new ProjectIdentifierException("Project Identifier " + projectIdentifier + " is not found");
        }
        return project;
    }

    @Override
    public Iterable<Project> listOfProject(String projectLeader) {
        return projectRepository.findAllByProjectLeader(projectLeader);
    }

    @Override
    public void deleteProject(String projectIdentifier, String projectLeader) {
        Project project = findByProjectIdentifierAndProjectLeader(projectIdentifier, projectLeader).get();
        projectRepository.delete(project);
    }
}
