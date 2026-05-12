package service;

import model.user;

import java.util.ArrayList;

public class userService {

    private ArrayList<user> userArr = new ArrayList<>();

    public void addUser(user user) {

        userArr.add(user);

        System.out.println("User registered successfully!");
    }

    public ArrayList<user> getAllUsers() {
        return userArr;
    }

    public user findUserById(String userId) {

        for (user user : userArr) {

            if (user.getUserId().equals(userId)) {

                return user;
            }
        }

        return null;
    }
}