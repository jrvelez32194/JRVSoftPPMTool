package jrvsoft.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Backlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int PTSequence = 0;
    private String projectIdentifier;

    // OneToOne with project
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    private Project project;

    // One to Many with project task
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "backlog")
    List<ProjectTask> projectTaskList = new ArrayList<>();

    public Backlog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPTSequence() {
        return PTSequence;
    }

    public void setPTSequence(int PTSequence) {
        this.PTSequence = PTSequence;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<ProjectTask> getProjectTaskList() {
        return projectTaskList;
    }

    public void setProjectTaskList(List<ProjectTask> projectTaskList) {
        this.projectTaskList = projectTaskList;
    }
}
