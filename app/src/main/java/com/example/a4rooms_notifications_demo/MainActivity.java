package com.example.a4rooms_notifications_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a4rooms_notifications_demo.adapter.NotificationAdapter;
import com.example.a4rooms_notifications_demo.room.Notification;
import com.example.a4rooms_notifications_demo.viewmodel.NotificationViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NotificationViewModel viewModel;
    private TextView unread;
   private List<Notification> notificationsGlob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationsGlob=new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        TextView tv_mark_all_as_read = findViewById(R.id.tv_mark_all);

        unread = findViewById(R.id.tv_unread);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final NotificationAdapter adapter = new NotificationAdapter();
        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(NotificationViewModel.class);
        viewModel.getAllNotifications().observe(this, new Observer<List<Notification>>() {
            @Override
            public void onChanged(List<Notification> notifications) {
                adapter.submitList(notifications);
                if (notificationsGlob!=null){
                notificationsGlob.clear();}
                notificationsGlob.addAll(notifications);
                int counter = 0;
                for (Notification e :
                        notifications) {
                    if (!e.getIs_read()) counter++;
                }
                unread.setText("Unread: " + counter);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getNotyat(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Notification Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(notification -> {
            notification.setIs_read(true);
            viewModel.update(notification);
            adapter.notifyDataSetChanged();

        });
        tv_mark_all_as_read.setOnClickListener(view -> {

            for (Notification e:
                 notificationsGlob) {
                e.setIs_read(true);
                viewModel.update(e);
                adapter.notifyDataSetChanged();
            }

        });

    }
}