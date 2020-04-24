package com.example.betav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.example.betav.FBreff.mAuth;
import static com.example.betav.FBreff.refUsers;

public class MainActivity extends AppCompatActivity {

        Intent t;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            t = new Intent(MainActivity.this, mainMaintenance.class);
            startActivity(t);

        }
}
