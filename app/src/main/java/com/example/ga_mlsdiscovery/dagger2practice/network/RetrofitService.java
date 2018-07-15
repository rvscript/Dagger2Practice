package com.example.ga_mlsdiscovery.dagger2practice.network;

import android.text.TextUtils;

import com.example.ga_mlsdiscovery.dagger2practice.application.Dagger2Application;

import javax.inject.Inject;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Basic Retrofit with authentication
public class RetrofitService {
    @Inject
    AuthenticationInterceptor authenticationInterceptor;

    private static final String BASE_URL = "https://api.github.com/";
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    @Inject
    public RetrofitService() {
        //dagger
        Dagger2Application.getApp().getInterceptorComponent().inject(this);
    }

    //adding a logging interceptor
    private HttpLoggingInterceptor httpLoggingInterceptor =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    //No Authentication
    public <S> S createService(Class<S> Loginservice){
        return createService(Loginservice, null , null);
    }

    //overwritten createService method for UN and PW authentication
    public <S> S createService(Class<S> endpoint, String username, String password) {
        if(!TextUtils.isEmpty(username)&& !TextUtils.isEmpty(password)){
            //use OkHttp's "credentials.basic(username, password);
            String credentials = Credentials.basic(username, password);
            //add auth interceptor using the created credentials
            httpClient.addInterceptor(
                    authenticationInterceptor.getNewInstance(credentials));
        }
        httpClient.addInterceptor(httpLoggingInterceptor);
        builder.client(httpClient.build());
        Retrofit retrofit = builder.build();

        return retrofit.create(endpoint);
    }
}
