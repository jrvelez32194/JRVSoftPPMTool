package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.Project;
import jrvsoft.ppmtool.exception.Exception;
import jrvsoft.ppmtool.repositories.ProjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectRepo;

    @Override
    public Project saveOrUpdate(Project project) {
        if(projectRepo.findByProjectIdentifier(project.getProjectIdentifier()).isPresent()){
            throw new Exception("Project Identifier " + project.getProjectIdentifier() + " is Already Exist");
        }
        return projectRepo.save(project);
    }
}
