package com.github.ian.githubusers.services;

import com.github.ian.githubusers.model.User;
import com.github.ian.githubusers.model.Users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ian on 8/28/2017.
 */
public interface IGitHubHttp {

    @GET("users/{id}")
    Call<User> findUserByID(@Path("id") int id);

    @GET("users")
    Call<ArrayList<Users>> findAllUsers();
}
