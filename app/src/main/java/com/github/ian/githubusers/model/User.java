package com.github.ian.githubusers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ian on 8/28/2017.
 */
public class User {
    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("avatar_url")
    @Expose
    String avatar_url;

    @SerializedName("html_url")
    @Expose
    String html_url;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("followers")
    @Expose
    int followers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", avatar_url='" + avatar_url + '\'' +
                ", html_url='" + html_url + '\'' +
                ", name='" + name + '\'' +
                ", followers=" + followers +
                '}';
    }
}
