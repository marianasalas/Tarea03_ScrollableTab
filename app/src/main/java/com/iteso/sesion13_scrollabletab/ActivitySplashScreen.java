package com.iteso.sesion13_scrollabletab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iteso.sesion13_scrollabletab.Beans.User;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {
    public static int SPLASH_SCREEN_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                User user = loadUser();
                Intent intent;
                if (user.isLogged()) {
                    intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
                } else {
                    intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
                }

                startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

    public User loadUser() {
        SharedPreferences sharedPreferences =
                getSharedPreferences(getString(R.string.preferences_file_key),
                        MODE_PRIVATE);

        User user = new User();
        user.setName(sharedPreferences.getString("NAME", null));
        user.setPassword(sharedPreferences.getString("PASS", null));
        user.setLogged(sharedPreferences.getBoolean("LOGGED", false));
        sharedPreferences = null;
        return user;
    }

}
