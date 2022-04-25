package jrvsoft.ppmtool.exception;

public class ProjectIdExceptionResponse {

    private String id;

    public ProjectIdExceptionResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
