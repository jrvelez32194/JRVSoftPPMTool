package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.Project;

import java.util.Optional;

public interface ProjectService {
    public Project saveOrUpdate(Project project);
    public Optional<Project> findByProjectIdentifier(String projectIdentifier);
}
