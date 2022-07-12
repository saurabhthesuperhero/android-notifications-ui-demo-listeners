package com.example.a4rooms_notifications_demo.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Notification.class, version = 1)
public abstract class NotificationDatabase extends RoomDatabase {
    private static NotificationDatabase instance;

    public abstract NotificationDao notificationDao();

    public static synchronized NotificationDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NotificationDatabase.class, "fourrooms_v1").fallbackToDestructiveMigration().addCallback(callback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }

    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NotificationDao notificationDao;

        public PopulateDbAsyncTask(NotificationDatabase notificationDatabase) {
            this.notificationDao = notificationDatabase.notificationDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notificationDao.insert(new Notification("1", "dance india dance", "this is description",
                    "action", "s", String.valueOf(System.currentTimeMillis()), "https://picsum.photos/200/300", "ff5733 ", false));
            notificationDao.insert(new Notification("2", "dance india dance 2ribar", "this is description",
                    "action", "s", String.valueOf(System.currentTimeMillis()), "https://picsum.photos/200/300", "ff5733 ", true));
          notificationDao.insert(new Notification("3", "dance india 3 baar", "this is description",
                    "action", "s", String.valueOf(System.currentTimeMillis()), "https://picsum.photos/200/300", "ff5733 ", false));
            notificationDao.insert(new Notification("4", "dance india 4 baar", "this is description",
                    "action", "s", String.valueOf(System.currentTimeMillis()), "https://picsum.photos/200/300", "ff5733 ", false));
            notificationDao.insert(new Notification("5", "dance india 5 baar", "this is description",
                    "action", "s", String.valueOf(System.currentTimeMillis()), "https://picsum.photos/200/300", "ff5733 ", true));
            notificationDao.insert(new Notification("6", "dance india 6 baar", "this is description",
                    "action", "s", String.valueOf(System.currentTimeMillis()), "https://picsum.photos/200/300", "ff5733 ", false));
            notificationDao.insert(new Notification("7", "dance india 7 baar", "this is description",
                    "action", "s", String.valueOf(System.currentTimeMillis()), "https://picsum.photos/200/300", "ff5733 ", true));
            notificationDao.insert(new Notification("8", "dance india 8 baar", "this is description",
                    "action", "s", String.valueOf(System.currentTimeMillis()), "https://picsum.photos/200/300", "ff5733 ", false));

            return null;
        }
    }
}
