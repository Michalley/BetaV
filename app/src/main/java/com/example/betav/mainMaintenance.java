package com.example.betav;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.betav.FBreff.mAuth;
import static com.example.betav.FBreff.refAre;
import static com.example.betav.FBreff.refCat;
import static com.example.betav.FBreff.refCom;
import static com.example.betav.FBreff.refUsers;

public class mainMaintenance extends AppCompatActivity implements AdapterView.OnItemSelectedListener,AdapterView.OnItemClickListener {

    ListView lv;

    ArrayList<Complain> comValue = new ArrayList<>();
    ArrayList<String> cValue = new ArrayList<>();


    String str1, str2, str3 ,uid;
    int str4;

    int value1;
    Intent t;

    ArrayAdapter<String> adp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_maintenance);

            lv = (ListView) findViewById(R.id.lv);

            lv.setOnItemClickListener(this);
            lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            lv.setOnItemClickListener(this);
            lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            fillListView();
            getUser();
    }

    public void fillListView (){
        refCom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cValue.clear();
                comValue.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    str1 = (String) data.getKey();
                    Complain complain =data.getValue(Complain.class);
                    comValue.add(complain);
                    str2 = complain.getTime();
                    str3 = complain.getDate();
                    str4 = complain.getEmergency();
                    cValue.add(str1);
                }
                adp = new ArrayAdapter<String>(mainMaintenance.this,R.layout.support_simple_spinner_dropdown_item,cValue);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = (String) lv.getItemAtPosition(position);
        Intent a;
        a = new Intent (mainMaintenance.this, complaintPage.class);
        a.putExtra("name",text);
        startActivity(a);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("History");
        menu.add("Profile");
        menu.add("Credits");
        menu.add("Category");
        menu.add("Area");
        menu.add("Permits");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //activity menu selection
        String st = item.getTitle().toString();
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
        if (st.equals("Area")) {
            t = new Intent(this, Areas.class);
            startActivity(t);
        }
        if (st.equals("Credits")) {
            Toast.makeText(mainMaintenance.this, "Credits", Toast.LENGTH_LONG).show();
        }
        return true;
    }

}
