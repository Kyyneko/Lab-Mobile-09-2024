package com.example.tugaspraktikum_4;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static List<User> userList = new ArrayList<>();

    public static List<User> getUserData() {
        // Buat instance baru dari userList jika null
        if (userList == null) {
            userList = new ArrayList<>();
        }

        // Jika userList belum diisi, tambahkan data pengguna ke dalam list
        if (userList.isEmpty()) {
            userList.add(new User("Adel", "Reva Fidela Adel Pantjoro", "Haii Gess!!", R.drawable.pp2, R.drawable.post2));
            userList.add(new User("Zee", "Azizi Shafa Azadel", "Capek Ngoding :(", R.drawable.pp3, R.drawable.post3));
            userList.add(new User("Christy", "Angelina Christy", "I Love Youu", R.drawable.pp4, R.drawable.post4));
        }

        return userList;
    }
}

