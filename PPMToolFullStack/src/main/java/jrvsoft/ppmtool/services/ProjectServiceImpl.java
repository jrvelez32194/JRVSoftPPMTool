package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.Project;
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
        return projectRepo.save(project);
    }
}
