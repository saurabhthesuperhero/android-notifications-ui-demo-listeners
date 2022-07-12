package com.example.a4rooms_notifications_demo.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notifications_table")
public class Notification {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String notification_id;
    private String notification_title;
    private String notification_description;
    private String click_action;
    private String additional_data;
    private String notification_datetime;

    public Notification() {
    }

    public Notification(String notification_id, String notification_title, String notification_description, String click_action, String additional_data, String notification_datetime, String icon_url, String notification_color, Boolean is_read) {
        this.notification_id = notification_id;
        this.notification_title = notification_title;
        this.notification_description = notification_description;
        this.click_action = click_action;
        this.additional_data = additional_data;
        this.notification_datetime = notification_datetime;
        this.icon_url = icon_url;
        this.notification_color = notification_color;
        this.is_read = is_read;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(String notification_id) {
        this.notification_id = notification_id;
    }

    public String getNotification_title() {
        return notification_title;
    }

    public void setNotification_title(String notification_title) {
        this.notification_title = notification_title;
    }

    public String getNotification_description() {
        return notification_description;
    }

    public void setNotification_description(String notification_description) {
        this.notification_description = notification_description;
    }

    public String getClick_action() {
        return click_action;
    }

    public void setClick_action(String click_action) {
        this.click_action = click_action;
    }

    public String getAdditional_data() {
        return additional_data;
    }

    public void setAdditional_data(String additional_data) {
        this.additional_data = additional_data;
    }

    public String getNotification_datetime() {
        return notification_datetime;
    }

    public void setNotification_datetime(String notification_datetime) {
        this.notification_datetime = notification_datetime;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getNotification_color() {
        return notification_color;
    }

    public void setNotification_color(String notification_color) {
        this.notification_color = notification_color;
    }

    public Boolean getIs_read() {
        return is_read;
    }

    public void setIs_read(Boolean is_read) {
        this.is_read = is_read;
    }

    private String icon_url;
    private String notification_color;
    private Boolean is_read;
}
