package com.example.betav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.example.betav.FBreff.mAuth;
import static com.example.betav.FBreff.refUsers;

public class Profile extends AppCompatActivity {

    TextView tvN,tvNL,tvE,tvP,tvL;

    String uid; int value1;
    Intent t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvN = (TextView) findViewById(R.id.tvN);
        tvNL = (TextView) findViewById(R.id.tvNL);
        tvE = (TextView) findViewById(R.id.tvE);
        tvP = (TextView) findViewById(R.id.tvP);
        tvL = (TextView) findViewById(R.id.tvL);

        getUser();
    }

    public void getUser() {
        uid = mAuth.getInstance().getCurrentUser().getUid();
        refUsers.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User us = dataSnapshot.getValue(User.class);
                String name = us.getName();
                tvN.setText(name);
                String lastname = us.getlName();
                tvNL.setText(lastname);
                String email = us.getEmail();
                tvE.setText(email);
                String phone = us.getPhone();
                tvP.setText(phone);
                int level = us.getLevel();
                if (level==1){
                    tvL.setText("Maintenance");
                }
                if (level==2){
                    tvL.setText("Responsible");
                }
                if (level==3){
                    tvL.setText("Student");
                }
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
        menu.add("Back To Main Page");
        menu.add("Credits");
        if (value1 == 2) {
            menu.add("Permits");
        }
        if (value1==1){
            menu.add("Category");
            menu.add("Areas");
            menu.add("Permits");
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
            Toast.makeText(Profile.this, "Credits", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
