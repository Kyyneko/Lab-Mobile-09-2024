package com.example.pmd_3;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private final Integer image, post,story;
    private final String name, followers, following, caption;

    // Constructor
    public User(String name, String followers, String following, String caption , Integer image, Integer post, Integer story) {
        this.name = name;
        this.followers = followers;
        this.image = image;
        this.post = post;
        this.following = following;
        this.caption = caption;
        this.story = story;
    }

    // Getter methods
    public int getStory() {
        return story;
    }
    public int getImage() {
        return image;
    }

    public int getPost() {
        return post;
    }

    public String getName() {
        return name;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    public String getCaption() {
        return caption;
    }

    // Parcelable implementation
    protected User(Parcel in) {
        image = in.readInt();
        post = in.readInt();
        story = in.readInt();
        name = in.readString();
        followers = in.readString();
        following = in.readString();
        caption = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeInt(post);
        dest.writeInt(story);
        dest.writeString(name);
        dest.writeString(followers);
        dest.writeString(following);
        dest.writeString(caption);
    }

    @Override
    public int describeContents() {
        return 0;
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
}
