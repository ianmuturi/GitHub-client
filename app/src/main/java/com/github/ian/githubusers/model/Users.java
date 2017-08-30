package com.github.ian.githubusers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ian on 8/29/2017.
 */
public class Users {
    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("login")
    @Expose
    String login;

    @SerializedName("avatar_url")
    @Expose
    String avatar_url;
    public Users(String login) {
        super();
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                '}';
    }
}
