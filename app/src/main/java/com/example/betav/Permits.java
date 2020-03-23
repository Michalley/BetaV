package com.example.betav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.example.betav.FBreff.mAuth;
import static com.example.betav.FBreff.refUsers;

public class Permits extends AppCompatActivity {

    Intent t;
    String uid;
    int value1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permits);
        getUser();
    }

    public void getUser() {
        uid = mAuth.getInstance().getCurrentUser().getUid();
        refUsers.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User us = dataSnapshot.getValue(User.class);
                value1=us.getLevel();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("History");
        menu.add("Profile");
        menu.add("Credits");
        menu.add("Back To Main Page");
        if (value1==1){
            menu.add("Category");
            menu.add("Areas");
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //activity menu selection
        String st = item.getTitle().toString();
        if (st.equals("Back To Main Page")) {
            if (value1==1){
                t = new Intent(this, mainMaintenance.class);
                startActivity(t);
            }
            if (value1==2){
                t = new Intent(this, MainStuRespo.class);
                startActivity(t);
            }
            if (value1==3){
                t = new Intent(this, MainStuRespo.class);
                startActivity(t);
            }
        }
        if (st.equals("History")) {
            t = new Intent(this, History.class);
            startActivity(t);
        }
        if (st.equals("Profile")) {
            t = new Intent(this, Profile.class);
            startActivity(t);
        }
        if (st.equals("Permits")) {
            t = new Intent(this, Permits.class);
            startActivity(t);
        }
        if (st.equals("Category")) {
            t = new Intent(this, Categories.class);
            startActivity(t);
        }
        if (st.equals("Areas")) {
            t = new Intent(this, Areas.class);
            startActivity(t);
        }
        if (st.equals("Credits")) {
            Toast.makeText(Permits.this, "Credits", Toast.LENGTH_LONG).show();
        }
        return true;
    }

}
