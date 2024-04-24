package com.example.tugaspraktikum_4;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String username;
    private String name;
    private String caption;
    private int imageProfile;
    private Object imagePost;

    // Constructor
    public User(String username, String name, String caption, int imageProfile, Object imagePost) {
        this.username = username;
        this.name = name;
        this.caption = caption;
        this.imageProfile = imageProfile;
        this.imagePost = imagePost;
    }

    // Parcelable implementation
    protected User(Parcel in) {
        username = in.readString();
        name = in.readString();
        caption = in.readString();
        imageProfile = in.readInt();

        // Pengecekan tipe data saat membaca imagePost dari Parcel
        if (in.readByte() == 0) {
            imagePost = null;
        } else {
            imagePost = in.readInt(); // Jika integer, maka membaca integer
        }
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(caption);
        dest.writeInt(imageProfile); // Menulis ID sumber daya gambar sebagai int

        // Pengecekan tipe data saat menulis imagePost ke Parcel
        if (imagePost instanceof Integer) {
            dest.writeByte((byte) 1); // Menulis byte 1 untuk menandakan tipe data integer
            dest.writeInt((Integer) imagePost);
        } else {
            dest.writeByte((byte) 0); // Menulis byte 0 untuk menandakan null atau URI
        }
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(int imageProfile) {
        this.imageProfile = imageProfile;
    }

    public Object getImagePost() {
        return imagePost;
    }

    public void setImagePost(Object imagePost) {
        this.imagePost = imagePost;
    }
}
