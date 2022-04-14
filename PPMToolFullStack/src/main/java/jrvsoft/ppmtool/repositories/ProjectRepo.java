package jrvsoft.ppmtool.repositories;

import jrvsoft.ppmtool.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ProjectRepo extends JpaRepository<Project, Long> {

    Optional<Project> findByProjectIdentifier(String projectIdentifier);

}
