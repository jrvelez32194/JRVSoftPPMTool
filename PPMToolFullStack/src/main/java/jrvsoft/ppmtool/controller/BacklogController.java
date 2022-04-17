package jrvsoft.ppmtool.controller;

import jrvsoft.ppmtool.domain.ProjectTask;
import jrvsoft.ppmtool.services.MapValidationErrorService;
import jrvsoft.ppmtool.services.ProjectTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
        return new ResponseEntity<ProjectTask>(projectTaskService.getProjectTaskByProjectIdentifierByProjectSequence(projectIdentifier, projectSequence), HttpStatus.OK);
    }

    @PatchMapping("/{projectIdentifier}/{projectSequence}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask ,
                                               @PathVariable String projectIdentifier,
                                               @PathVariable String projectSequence,
                                               BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.getMapValidationError(result);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<ProjectTask>(projectTaskService.updateByProject(projectTask, projectIdentifier, projectSequence), HttpStatus.OK);
    }


}
