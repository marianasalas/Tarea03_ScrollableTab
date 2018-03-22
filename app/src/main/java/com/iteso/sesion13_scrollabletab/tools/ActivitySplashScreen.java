package com.iteso.sesion13_scrollabletab.tools;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iteso.sesion13_scrollabletab.Beans.City;
import com.iteso.sesion13_scrollabletab.Beans.Store;
import com.iteso.sesion13_scrollabletab.Beans.User;
import com.iteso.sesion13_scrollabletab.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import DataBase.DataBaseHandler;
import DataBase.StoreControl;

public class ActivitySplashScreen extends AppCompatActivity {
    public static int SPLASH_SCREEN_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /**Al abrir la aplicación el ActivitySplashScreen deberá hacer una consulta a la BD para
         obtener todas las tiendas con las que se cuentan mediante el método getStores, SOLO SI SE
         TIENEN 0 tiendas se deberán crear 3 tiendas nuevas llamando al método addStore. Significa
         que solo se insertaran una sola vez.
         */
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                StoreControl storeControl = new StoreControl();
                DataBaseHandler dh = DataBaseHandler.getInstance(ActivitySplashScreen.this);
                ArrayList<Store> stores = storeControl.getStores(dh);
                Store store1 = new Store(0, "Dell", "555510036017", 2,5.1,6.0,new City(2, "Tequepexpan"));
                Store store2 = new Store(1, "BestBuy", "3315474896", 0,5.1,6.0,new City(1, "Guadalajara"));
                Store store3 = new Store(2, "St. Jhonny", "018001005664", 1,5.8,6.3,new City(7, "Tlaquepaque"));

                if(stores.isEmpty()){
                    storeControl.addStore(store1,dh);
                    storeControl.addStore(store2, dh);
                    storeControl.addStore(store3,dh);
                }

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
