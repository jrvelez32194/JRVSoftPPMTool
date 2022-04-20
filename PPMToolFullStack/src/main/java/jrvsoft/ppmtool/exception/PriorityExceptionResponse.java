package jrvsoft.ppmtool.exception;

public class PriorityExceptionResponse {

    private String priority;

    public PriorityExceptionResponse(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
