package com.example.ga_mlsdiscovery.dagger2practice.network;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {
    private String authToken;

    @Inject
    public AuthenticationInterceptor() {
    }

    public AuthenticationInterceptor getNewInstance(String authToken){
        this.authToken = authToken;
        return this;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        //set or override the Authorization header
        //keep the request body
        Request.Builder builder = original.newBuilder()
                .header("Authoriztion", authToken)
                .method(original.method(), original.body());

        Request request = builder.build();
        return  chain.proceed(request);
    }
}