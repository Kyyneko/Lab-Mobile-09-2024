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
            userList.add(new User("Narto-Kun", "Uzumaki Naruto", "Saskehhhh", R.drawable.ppnaruto, R.drawable.postnaruto));
            userList.add(new User("Mama'na Borto", "Hyuga Hinata", "Nartooo kunnggggg", R.drawable.pphinata, R.drawable.posthinata));
            userList.add(new User("Saskehh", "Uchiha Sasuke", "Cihhhhh", R.drawable.ppsasuke, R.drawable.postsasuke));
            userList.add(new User("I'm Beban", "Haruno Sakura", "Saskehhhhhh kunnnnnn!!", R.drawable.ppsakura, R.drawable.postsakura));
            userList.add(new User("Hokage-sama", "Hatake Kakashi", "____________", R.drawable.ppkakashi, R.drawable.postkakashi));
            userList.add(new User("Sai", "Sai Nih Bouss", "Santai Duluu gaa sehh", R.drawable.ppsai, R.drawable.postsai));
            userList.add(new User("Tante Ino", "Yamanaka Ino", "Tantee Culik Nihh Awww", R.drawable.ppino, R.drawable.postino));
            userList.add(new User("Abang Zoro Ganteng", "Zoro Beckham", "Haii Ladiess <3", R.drawable.ppzoro, R.drawable.postzoro));
        }

        return userList;
    }
}

