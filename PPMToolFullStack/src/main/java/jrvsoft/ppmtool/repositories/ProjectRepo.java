package jrvsoft.ppmtool.repositories;

import jrvsoft.ppmtool.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {

}
