package service;

import model.user;
import java.util.ArrayList;

public class authService {

    private ArrayList<user> userArr;

    public authService(ArrayList<user> userArr) {

        this.userArr = userArr;
    }

    public user login(String email, String password) {

        for (user user : userArr) {

            if (user.getEmail().equals(email)
                    && user.getPassword().equals(password)) {

                System.out.println("Login successful!");

                return user;
            }
        }

        System.out.println("Invalid email or password!");

        return null;
    }
}