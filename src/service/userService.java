package service;

import exception.EmptyFieldException;
import exception.UserNotFoundException;
import model.user;

import java.util.ArrayList;

public class userService {

    private ArrayList<user> users = new ArrayList<>();

    public void addUser(user newUser) throws EmptyFieldException {

        if (newUser == null) {
            throw new EmptyFieldException("User cannot be null.");
        }

        if (newUser.getUserId() == null || newUser.getUserId().trim().isEmpty()) {
            throw new EmptyFieldException("User ID cannot be empty.");
        }

        if (newUser.getName() == null || newUser.getName().trim().isEmpty()) {
            throw new EmptyFieldException("User name cannot be empty.");
        }

        if (newUser.getEmail() == null || newUser.getEmail().trim().isEmpty()) {
            throw new EmptyFieldException("User email cannot be empty.");
        }

        users.add(newUser);
    }

    public ArrayList<user> getAllUsers() {
        return users;
    }

    public user findUserById(String userId) throws EmptyFieldException, UserNotFoundException {

        if (userId == null || userId.trim().isEmpty()) {
            throw new EmptyFieldException("User ID cannot be empty.");
        }

        for (user user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }

        throw new UserNotFoundException("User not found with ID: " + userId);
    }
}