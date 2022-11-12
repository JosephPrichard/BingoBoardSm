package edu.utdallas.bbsm.account;

public class SignOutDto {

    private String username;

    public SignOutDto() {
    }

    public SignOutDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "SignOutDto{" +
                "username='" + username + '\'' +
                '}';
    }
}
