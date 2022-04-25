package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    Project saveOrUpdate(Project project, String projectLeader);

    Optional<Project> findByProjectIdentifierAndProjectLeader(String projectIdentifier, String projectLeader);

    Optional<Project> findByProjectIdentifier(String projectIdentifier);

    Iterable<Project> listOfProject(String projectLeader);

    void deleteProject(String projectIdentifier, String projectLeader);
}
