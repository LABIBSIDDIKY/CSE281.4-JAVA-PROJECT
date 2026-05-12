package model;

public class admin extends user {

    private String role;

    public admin(String userId, String name, String email,
                 String phone, String password,
                 String role) {

        super(userId, name, email, phone, password);

        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
