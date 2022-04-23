package jrvsoft.ppmtool.repositories;

import jrvsoft.ppmtool.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByProjectIdentifierAndProjectLeader(String projectIdentifier, String projectLeader);
    List<Project> findAllByProjectLeader(String projectLeader);

}
