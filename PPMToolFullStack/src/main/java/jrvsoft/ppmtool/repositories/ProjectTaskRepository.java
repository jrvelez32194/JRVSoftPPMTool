package jrvsoft.ppmtool.repositories;

import jrvsoft.ppmtool.domain.Backlog;
import jrvsoft.ppmtool.domain.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Long> {
    List<ProjectTask> findByProjectIdentifierOrderByPriority(String projectIdentifier);
    ProjectTask  findByProjectSequence(String projectSequence);
}
