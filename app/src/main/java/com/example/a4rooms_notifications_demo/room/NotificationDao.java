package com.example.a4rooms_notifications_demo.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NotificationDao {
    @Insert
    void insert(Notification notification);
    @Update
    void update(Notification notification);

    @Delete
    void delete(Notification notification);

    @Query("DELETE FROM Notifications_table")
    void deleteAllNotifications();

    @Query("SELECT * FROM Notifications_table")
    LiveData<List<Notification>> getAllNotifications();
}
