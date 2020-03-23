package com.example.betav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.betav.FBreff.mAuth;
import static com.example.betav.FBreff.refCom;
import static com.example.betav.FBreff.refHis;
import static com.example.betav.FBreff.refUsers;

public class History extends AppCompatActivity {

    ListView lv;

    Intent t;

    ArrayList<String> cValue = new ArrayList<>();

    String str1,uid; int value1;

    ArrayAdapter<String> adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lv = (ListView) findViewById(R.id.lv);
        fillListView();
        getUser();
    }

    public void fillListView (){
        refHis.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cValue.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    str1 = (String) data.getKey();
                    cValue.add(str1);
                }
                adp = new ArrayAdapter<String>(History.this,R.layout.support_simple_spinner_dropdown_item,cValue);
                lv.setAdapter(adp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
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
        menu.add("Profile");
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
            Toast.makeText(History.this, "Credits", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
