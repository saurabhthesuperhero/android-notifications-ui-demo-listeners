package com.example.a4rooms_notifications_demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a4rooms_notifications_demo.R;
import com.example.a4rooms_notifications_demo.room.Notification;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NoteHolder> {
    private List<Notification> notifications = new ArrayList<>();
    private Context context;
    private OnItemClickListener listener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item, parent, false);
        this.context = parent.getContext();
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Notification current = notifications.get(position);
        holder.textViewTitle.setText(current.getNotification_title());
        holder.textViewDescription.setText(current.getNotification_description());
        Glide.with(context).load(current.getIcon_url()).into(holder.circleImageView);
        DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        long milliSeconds = Long.parseLong(current.getNotification_datetime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        if (current.getIs_read()) {
            holder.isread.setVisibility(View.INVISIBLE);
        } else {
            holder.isread.setVisibility(View.VISIBLE);
        }

        holder.textViewTime.setText(formatter.format(calendar.getTime()));

    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
        notifyDataSetChanged();
    }

    public Notification getNotyat(int position) {
        return notifications.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewTime;
        private View isread;
        private CircleImageView circleImageView;

        public NoteHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tv_title_notification);
            textViewDescription = itemView.findViewById(R.id.tv_description_notification);
            textViewTime = itemView.findViewById(R.id.tv_date_notification);
            circleImageView = itemView.findViewById(R.id.imageView);
            isread = itemView.findViewById(R.id.view_notread);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(notifications.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Notification notification);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}