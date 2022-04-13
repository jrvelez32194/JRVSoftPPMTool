package jrvsoft.ppmtool.controller;

import jrvsoft.ppmtool.domain.Project;
import jrvsoft.ppmtool.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/project")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<Project> createNewProject(@RequestBody @Valid Project project){
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

}
