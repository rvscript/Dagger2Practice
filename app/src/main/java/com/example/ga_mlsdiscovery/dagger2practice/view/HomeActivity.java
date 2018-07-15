package com.example.ga_mlsdiscovery.dagger2practice.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ga_mlsdiscovery.dagger2practice.application.Dagger2Application;
import com.example.ga_mlsdiscovery.dagger2practice.R;
import com.example.ga_mlsdiscovery.dagger2practice.model.User;
import com.example.ga_mlsdiscovery.dagger2practice.network.RetrofitService;
import com.example.ga_mlsdiscovery.dagger2practice.network.endpoints.LoginService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    @Inject
    RetrofitService retrofitService;

    private User user;
    private int id = -1;

    private Button btRetrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btRetrofit = findViewById(R.id.button1);
        btRetrofit.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ActivityCompat.requestPermissions(HomeActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }
        Timber.d("Starting Home Activity");

        //dagger
        Dagger2Application.getApp().getHomeActivityComponent().inject(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(HomeActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onClick(View v) {
        id = v.getId();
        switch (id) {
            case R.id.button1:
                String username = "rvscript";
//Line to replace by dagger
                //LoginService loginService = RetrofitService.createService(LoginService.class);
                 LoginService loginService = retrofitService.createService(LoginService.class);
                Call<User> call = loginService.getUser(username);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            user = response.body();
                            Timber.d("onResponse: "+user.getId()+"\n"+ user.getAvatarUrl());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Timber.e( "onFailure: "+t.getMessage());
                    }
                });
                break;

        }
    }
}
