package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    public Project saveOrUpdate(Project project);
    public Optional<Project> findByProjectIdentifier(String projectIdentifier);
    public List<Project> listOfProject();
    public void deleteProject(String projectIdentifier);
}
