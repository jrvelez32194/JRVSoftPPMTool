package jrvsoft.ppmtool.exception;

public class UsernameNotExistResponse {
    private String username;

    public UsernameNotExistResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
