package service;

import exception.EmptyFieldException;
import exception.UserNotFoundException;
import model.user;

import java.util.ArrayList;

public class authService {

    private ArrayList<user> users;

    public authService(ArrayList<user> users) {
        this.users = users;
    }

    public user login(String email, String password)
            throws EmptyFieldException, UserNotFoundException {

        if (email == null || email.trim().isEmpty()) {
            throw new EmptyFieldException("Email cannot be empty.");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new EmptyFieldException("Password cannot be empty.");
        }

        for (user user : users) {
            if (user.getEmail().equals(email)
                    && user.getPassword().equals(password)) {
                return user;
            }
        }

        throw new UserNotFoundException("Invalid email or password.");
    }
}