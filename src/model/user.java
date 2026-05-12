package model;

public class user {

    private String userId;
    private String name;
    private String email;
    private String phone;
    private String password;

    public user(String userId, String name, String email,
                String phone, String password) {

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
