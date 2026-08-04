package com.example.carmarket.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.carmarket.database.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    long insert(User user);

    @Delete
    void delete(User... user);

    @Query("DELETE FROM User")
    void deleteAll();

    @Update
    int update(User... user);

    @Query("SELECT * FROM User")
    List<User> queryAll();

    @Query("SELECT * FROM User WHERE username = :username ORDER BY id DESC limit 1")
    User queryByName(String username);

    @Query("SELECT * FROM User WHERE email = :email ORDER BY id DESC limit 1")
    User queryByEmail(String email);
}
