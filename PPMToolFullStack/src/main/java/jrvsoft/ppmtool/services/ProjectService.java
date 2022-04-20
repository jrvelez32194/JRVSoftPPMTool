package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
     Project saveOrUpdate(Project project);
     Optional<Project> findByProjectIdentifier(String projectIdentifier);
     List<Project> listOfProject();
     void deleteProject(String projectIdentifier);
}
