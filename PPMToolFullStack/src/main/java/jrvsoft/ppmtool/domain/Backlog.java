package jrvsoft.ppmtool.domain;

import javax.persistence.*;

@Entity
@Table
public class Backlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int PTSequence = 0;
    private String projectIdentifier;

    // OneToOne with project

    // One to Many with project task


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
}
