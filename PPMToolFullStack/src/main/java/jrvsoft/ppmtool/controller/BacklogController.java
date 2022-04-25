package jrvsoft.ppmtool.controller;

import jrvsoft.ppmtool.domain.ProjectTask;
import jrvsoft.ppmtool.services.MapValidationErrorService;
import jrvsoft.ppmtool.services.ProjectTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

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
                                            BindingResult result,
                                            Principal principal) {

        ResponseEntity<?> errorMap = mapValidationErrorService.getMapValidationError(result);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<ProjectTask>(projectTaskService.addProject(projectIdentifier, projectTask, principal.getName()), HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public Iterable<ProjectTask> listOfProjectTask(@PathVariable String projectIdentifier, Principal principal) {
        return projectTaskService.listOfProjectTask(projectIdentifier, principal.getName());
    }

    @GetMapping("/{projectIdentifier}/{projectSequence}")
    public ResponseEntity<?> getProjectTask(@PathVariable String projectIdentifier, @PathVariable  String projectSequence, Principal principal){
        return new ResponseEntity<ProjectTask>(projectTaskService.getProjectTaskByProjectIdentifierByProjectSequence(projectIdentifier, projectSequence, principal.getName()), HttpStatus.OK);
    }

    @PatchMapping("/{projectIdentifier}/{projectSequence}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask ,BindingResult result,
                                               @PathVariable String projectIdentifier,
                                               @PathVariable String projectSequence,
                                               Principal principal
                                               ) {
        ResponseEntity<?> errorMap = mapValidationErrorService.getMapValidationError(result);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<ProjectTask>(projectTaskService.updateByProject(projectTask, projectIdentifier, projectSequence, principal.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/{projectIdentifier}/{projectSequence}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String projectIdentifier,
                                               @PathVariable String projectSequence,
                                               Principal principal){
        projectTaskService.deleteProjectTask(projectIdentifier, projectSequence, principal.getName());
        return new ResponseEntity<String>("Successfully Deleted", HttpStatus.OK);
    }

}
