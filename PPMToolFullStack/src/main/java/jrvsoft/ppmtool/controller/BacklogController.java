package jrvsoft.ppmtool.controller;

import jrvsoft.ppmtool.domain.Project;
import jrvsoft.ppmtool.domain.ProjectTask;
import jrvsoft.ppmtool.services.MapValidationErrorService;
import jrvsoft.ppmtool.services.ProjectService;
import jrvsoft.ppmtool.services.ProjectTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/backlog")
@CrossOrigin
public class BacklogController {
    private final ProjectTaskService projectTaskService;
    private final MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{projectIdentifier}")
    public ResponseEntity<?> addProjectTask(@PathVariable String projectIdentifier,
                                            @Valid @RequestBody ProjectTask projectTask,
                                            BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.getMapValidationError(result);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<ProjectTask>(projectTaskService.addProject(projectIdentifier, projectTask), HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public Iterable<ProjectTask> listOfProjectTask(@PathVariable String projectIdentifier) {
        return projectTaskService.listOfProjectTask(projectIdentifier);
    }

    @GetMapping("/{projectIdentifier}/{projectSequence}")
    public ResponseEntity<?> getProjectTask(@PathVariable String projectIdentifier, @PathVariable  String projectSequence){
        return new ResponseEntity<ProjectTask>(projectTaskService.getProjectTask(projectIdentifier, projectSequence), HttpStatus.OK);
    }

}
