package com.suman.notification;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.suman.notification.service.MyService;

public class MainActivity extends AppCompatActivity {
    private Button btn1, btn2 ,btn3,btn4;

    private NotificationManagerCompat notificationManagerCompat;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DisplayNotification();
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayNotification2();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMyService();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMyService();
            }
        });

    }

    private void stopMyService() {
        stopService(new Intent(this, MyService.class));
    }

    private void startMyService() {
        startService(new Intent(this, MyService.class));
    }

    int id =1;
    private void DisplayNotification2() {

        Notification notification1 = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_2)
                .setSmallIcon(R.drawable.ic_message_black_24dp)
                .setContentTitle("Second Message")
                .setContentText("Second Message body")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(id,notification1);
        id++;
    }

    private void DisplayNotification() {

            Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
                    .setSmallIcon(R.drawable.ic_sms_black_24dp)
                    .setContentTitle("First Message")
                    .setContentText("First Message body")
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .build();

            notificationManagerCompat.notify(id, notification);
            id++;
        }
    }

