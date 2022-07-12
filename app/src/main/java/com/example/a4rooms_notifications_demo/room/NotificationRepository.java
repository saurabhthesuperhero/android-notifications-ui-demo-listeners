package com.example.a4rooms_notifications_demo.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class NotificationRepository {
    private NotificationDao notificationDao;
    private LiveData<List<Notification>> allNotifications;

    public NotificationRepository(Application application) {
        NotificationDatabase notificationDatabase = NotificationDatabase.getInstance(application);
        notificationDao = notificationDatabase.notificationDao();
        allNotifications = notificationDao.getAllNotifications();
    }

    public void insert(Notification notification) {
        new InsertNotificationAsyncTask(notificationDao).execute(notification);
    }

    public void update(Notification notification) {
        new UpdateNotificationAsyncTask(notificationDao).execute(notification);

    }

    public void delete(Notification notification) {
        new DeleteNotificationAsyncTask(notificationDao).execute(notification);

    }

    public void deleteAllNotification() {
        new DeleteAllNotificationAsyncTask(notificationDao).execute();

    }

    public LiveData<List<Notification>> getAllNotifications() {
        return allNotifications;
    }

    private static class InsertNotificationAsyncTask extends AsyncTask<Notification, Void, Void> {
        private NotificationDao notificationDao;

        private InsertNotificationAsyncTask(NotificationDao notificationDao) {
            this.notificationDao = notificationDao;
        }

        @Override
        protected Void doInBackground(Notification... notifications) {
            notificationDao.insert(notifications[0]);
            return null;
        }
    }

    private static class UpdateNotificationAsyncTask extends AsyncTask<Notification, Void, Void> {
        private NotificationDao notificationDao;

        private UpdateNotificationAsyncTask(NotificationDao notificationDao) {
            this.notificationDao = notificationDao;
        }

        @Override
        protected Void doInBackground(Notification... notifications) {
            notificationDao.update(notifications[0]);
            return null;
        }
    }

    private static class DeleteNotificationAsyncTask extends AsyncTask<Notification, Void, Void> {
        private NotificationDao notificationDao;

        private DeleteNotificationAsyncTask(NotificationDao notificationDao) {
            this.notificationDao = notificationDao;
        }

        @Override
        protected Void doInBackground(Notification... notifications) {
            notificationDao.delete(notifications[0]);
            return null;
        }
    }

    private static class DeleteAllNotificationAsyncTask extends AsyncTask<Void, Void, Void> {
        private NotificationDao notificationDao;

        private DeleteAllNotificationAsyncTask(NotificationDao notificationDao) {
            this.notificationDao = notificationDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notificationDao.deleteAllNotifications();
            return null;
        }
    }

}
