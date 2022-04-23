package jrvsoft.ppmtool.controller;

import jrvsoft.ppmtool.domain.Project;
import jrvsoft.ppmtool.services.MapValidationErrorService;
import jrvsoft.ppmtool.services.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/project")
@CrossOrigin
public class ProjectController {

    private final ProjectService projectService;
    private final MapValidationErrorService mapValidationErrorService;

    @PostMapping()
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project
            , BindingResult result, Principal principal){

         ResponseEntity<?> errorMap = mapValidationErrorService.getMapValidationError(result);
         if(errorMap!=null) return errorMap;

        return new ResponseEntity<Project>(projectService.saveOrUpdate(project, principal.getName()), HttpStatus.CREATED);

    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> getProjectByProjectIdentifier(@PathVariable String projectIdentifier, Principal principal){
        return new ResponseEntity<Project>(projectService.findByProjectIdentifierAndProjectLeader(
                projectIdentifier , principal.getName()).get(), HttpStatus.OK);
    }

    @GetMapping("/list")
    public Iterable<Project> listOfProject(Principal principal){return projectService.listOfProject(principal.getName());}

    @DeleteMapping("/{projectIdentifier}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectIdentifier, Principal principal){
        projectService.deleteProject(projectIdentifier, principal.getName());
        return new ResponseEntity<String>("Project successfully Deleted", HttpStatus.OK);
    }

}
