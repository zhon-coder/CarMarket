package com.example.carmarket.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.carmarket.database.UserDao;
import com.example.carmarket.database.User;

@Database(
        entities = {User.class},
        version = 1,
        exportSchema = false
)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao UserDao();
}
