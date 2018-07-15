package com.example.ga_mlsdiscovery.dagger2practice.network.endpoints;

import com.example.ga_mlsdiscovery.dagger2practice.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LoginService {
    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);
}
