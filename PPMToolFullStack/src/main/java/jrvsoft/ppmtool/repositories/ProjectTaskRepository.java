package jrvsoft.ppmtool.repositories;

import jrvsoft.ppmtool.domain.Backlog;
import jrvsoft.ppmtool.domain.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Long> {
}
