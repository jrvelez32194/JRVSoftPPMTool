package jrvsoft.ppmtool.controller;

import jrvsoft.ppmtool.domain.Project;
import jrvsoft.ppmtool.services.MapValidationErrorService;
import jrvsoft.ppmtool.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/project")
public class ProjectController {

    private final ProjectService projectService;
    private final MapValidationErrorService mapValidationErrorService;

    @PostMapping()
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){

         ResponseEntity<?> errorMap = mapValidationErrorService.getMapValidationError(result);
         if(errorMap!=null) return errorMap;

        return new ResponseEntity<Project>(projectService.saveOrUpdate(project), HttpStatus.CREATED);

    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> getProjectByProjectIdentifier(@PathVariable String projectIdentifier){
        return new ResponseEntity<Project>(projectService.findByProjectIdentifier(projectIdentifier).get(), HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<Project> listOfProject(){return projectService.listOfProject();}

    @DeleteMapping("/{projectIdentifier}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectIdentifier){
        projectService.deleteProject(projectIdentifier);
        return new ResponseEntity<String>("Project successfully Deleted", HttpStatus.OK);
    }

}
