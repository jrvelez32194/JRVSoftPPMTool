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
                                            BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.getMapValidationError(result);
        if(errorMap!=null) return errorMap;

        return new ResponseEntity<ProjectTask>(projectTaskService.addProject(projectIdentifier, projectTask), HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public List<ProjectTask> listOfProjectTask(){return projectTaskService.listOfProjectTask();}

}
