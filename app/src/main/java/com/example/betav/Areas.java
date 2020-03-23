package com.example.betav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.betav.FBreff.mAuth;
import static com.example.betav.FBreff.refAre;
import static com.example.betav.FBreff.refCat;
import static com.example.betav.FBreff.refCom;
import static com.example.betav.FBreff.refUsers;


public class Areas extends AppCompatActivity implements AdapterView.OnItemSelectedListener ,AdapterView.OnItemClickListener{

    Spinner spA;
    ListView lv;

    TextView tv;

    ArrayList<String> alpValues1 = new ArrayList<String>();
    ArrayList<String> cValue = new ArrayList<>();
    ArrayList<String> aa = new ArrayList<>();
    ArrayList<String> bb = new ArrayList<>();

    String category,str,category1,uid;
    int value1;

    Intent t;

    ArrayAdapter<String> adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas);

        spA = (Spinner) findViewById(R.id.spA);
        spA.setOnItemSelectedListener(this);
        tv = (TextView) findViewById(R.id.tv);


        lv = (ListView) findViewById(R.id.lv);
        lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        fillSpinnerC();
        fillListView();
        getUser();
    }

    public void fillSpinnerC () {
        refAre.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                alpValues1.clear();
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    alpValues1.add(item.getValue().toString());
                }
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Areas.this, android.R.layout.simple_list_item_1, alpValues1);
                spA.setAdapter(adapter2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void fillListView (){
        refCom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cValue.clear();
                aa.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String str1 = (String) data.getKey();
                    Complain complain =data.getValue(Complain.class);
                    category1 = complain.getCategory();
                    aa.add(category1);
                    cValue.add(str1);
                }
                adp = new ArrayAdapter<String>(Areas.this,R.layout.support_simple_spinner_dropdown_item,cValue);
                lv.setAdapter(adp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bb.clear();
        if (parent.getId() == spA.getId()) {
            if (position == 0) {
                category = "All Categories";
                fillListView();
            }
            if (position == 1) {
                int count =0;
                category = "Air Conditioning";
                for (String c:aa){
                    if (c.equals(category)){
                        bb.add(cValue.get(count));
                        count++;
                    }
                }
                adp = new ArrayAdapter<String>(Areas.this,R.layout.support_simple_spinner_dropdown_item,bb);
                lv.setAdapter(adp);
            }
            if (position == 2) {
                int count =0;
                category = "Plumbing";
                for (String c:aa){
                    if (c.equals(category)){
                        bb.add(cValue.get(count));
                        count++;
                    }
                }
                adp = new ArrayAdapter<String>(Areas.this,R.layout.support_simple_spinner_dropdown_item,bb);
                lv.setAdapter(adp);
            }
            if (position == 3) {
                int count =0;
                category = "Electricity";
                for (String c:aa){
                    if (c.equals(category)){
                        bb.add(cValue.get(count));
                        count++;
                    }
                }
                adp = new ArrayAdapter<String>(Areas.this,R.layout.support_simple_spinner_dropdown_item,bb);
                lv.setAdapter(adp);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = (String) lv.getItemAtPosition(position);
        Intent a;
        a = new Intent (Areas.this, complaintPage.class);
        a.putExtra("name",text);
        startActivity(a);
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
        menu.add("History");
        menu.add("Back To Main Page");
        menu.add("Credits");
        if (value1 == 2) {
            menu.add("Permits");
        }
        if (value1==1){
            menu.add("Category");
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
        if (st.equals("History")) {
            t = new Intent(this, History.class);
            startActivity(t);
        }
        if (st.equals("Category")) {
            t = new Intent(this, Areas.class);
            startActivity(t);
        }
        if (st.equals("Credits")) {
            Toast.makeText(Areas.this, "Credits", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
