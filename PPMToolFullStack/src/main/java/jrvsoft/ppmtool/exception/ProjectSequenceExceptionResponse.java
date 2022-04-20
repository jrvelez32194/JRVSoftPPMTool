package jrvsoft.ppmtool.exception;

public class ProjectSequenceExceptionResponse {

    private String projectSequence;

    public ProjectSequenceExceptionResponse(String projectSequence) {
        this.projectSequence = projectSequence;
    }

    public String getProjectSequence() {
        return projectSequence;
    }

    public void setProjectSequence(String projectSequence) {
        this.projectSequence = projectSequence;
    }
}
