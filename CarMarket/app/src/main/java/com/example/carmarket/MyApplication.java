package com.example.carmarket;

import android.app.Application;
import androidx.room.Room;
import com.example.carmarket.database.UserDatabase;

public class MyApplication extends Application {
    private static MyApplication mApp;
    private UserDatabase userDB;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        userDB = Room.databaseBuilder(this, UserDatabase.class, "users.db")
                .addMigrations()
                .allowMainThreadQueries().build();

        NotificationHelper.getInstance().createChannels(this);
    }

    public static MyApplication getInstance() {
        return mApp;
    }

    public UserDatabase getUserDatabase() {
        return userDB;
    }
}
