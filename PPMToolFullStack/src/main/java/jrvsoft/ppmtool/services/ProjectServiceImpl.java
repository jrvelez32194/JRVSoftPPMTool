package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.Project;
import jrvsoft.ppmtool.exception.Exception;
import jrvsoft.ppmtool.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public Project saveOrUpdate(Project project) {
        project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
        // checking if exist
        if(projectRepository.findByProjectIdentifier(project.getProjectIdentifier()).isPresent()){
            throw new Exception("Project Identifier " + project.getProjectIdentifier() + " already exist");
        }
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> findByProjectIdentifier(String projectIdentifier) {
        Optional<Project> project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(!project.isPresent()){
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
