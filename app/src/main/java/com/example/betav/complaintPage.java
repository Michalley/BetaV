package com.example.betav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.example.betav.FBreff.mAuth;
import static com.example.betav.FBreff.mStorageRef;
import static com.example.betav.FBreff.refCom;
import static com.example.betav.FBreff.refHis;
import static com.example.betav.FBreff.refUsers;

public class complaintPage extends AppCompatActivity{

    String user,date,time,category,zone,notes,name,pic;
    int emergency,state;

    EditText etNO;
    TextView tvD, tvT,tvN,tvAre,tvCat;
    ImageView ivC;
    RadioGroup rg;
    RadioButton rb3,rb4,rb5;

    Intent gi,t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_page);


        tvN = (TextView) findViewById(R.id.tvN);
        etNO = (EditText) findViewById(R.id.etNo);
        tvD = (TextView) findViewById(R.id.tvD);
        tvT = (TextView) findViewById(R.id.tvT);
        tvAre = (TextView) findViewById(R.id.tvAre);
        tvCat = (TextView) findViewById(R.id.tvCat);
        ivC = (ImageView) findViewById(R.id.ivC);
        rg = (RadioGroup) findViewById(R.id.rg1);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);
        rb5 = (RadioButton) findViewById(R.id.rb5);


        gi = getIntent();
        name = gi.getStringExtra("name");
        refCom.child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Complain com = dataSnapshot.getValue(Complain.class);
                user = com.getUser();
                date = com.getDate();   tvD.setText(date);
                time = com.getTime();   tvT.setText(time);
                category = com.getCategory();   tvCat.setText(category);
                zone = com.getZone();   tvAre.setText(zone);
                emergency = com.getEmergency();
                state = com.getState();
                notes = com.getNotes();   etNO.setText(notes);
                pic = com.getPic();
                tvN.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //downloadImage(complaintPage.this,"image",DIRECTORY_DOWNLOAD,pic);
    }

    public void downloadImage(Context context,String fileName, String destinationDirectory, String url){
            DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse(url);
            DownloadManager.Request r = new DownloadManager.Request(uri);

            r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            r.setDestinationInExternalFilesDir(context,destinationDirectory,fileName);

            ivC.setImageURI(uri);
            dm.enqueue(r);
        }

    public void Back(View view) {
        Intent t = new Intent (complaintPage.this, mainMaintenance.class);
        startActivity(t);
    }

    public void save(View view) {
        notes = etNO.getText().toString();
        if (rb3.isChecked()){
            state=1;//started
        }
        if (rb4.isChecked()){
            state=2;//finished
        }
        if (rb5.isChecked()){
            state=0;//not
        }
        AlertDialog.Builder adb;
        adb = new AlertDialog.Builder(this);
        adb.setTitle("Saving...");
        adb.setMessage("Are You Sure?");
        adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (state==0) {
                    refCom.child(name).child("notes").setValue(notes);
                    t = new Intent(complaintPage.this, mainMaintenance.class);
                    startActivity(t);
                }
                if (state==1){
                    refCom.child(name).child("notes").setValue(notes);
                    refCom.child(name).child("state").setValue(1);
                    t = new Intent(complaintPage.this, mainMaintenance.class);
                    startActivity(t);
                }
                if (state==2){
                    refCom.child(name).removeValue();
                    Complain co= new Complain(user,date,time,category,zone,emergency,state,notes,name,pic);
                    refHis.child(name).setValue(co);
                    t = new Intent(complaintPage.this, mainMaintenance.class);
                    startActivity(t);
                }
            }
        });
        adb.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog ad = adb.create();
        ad.show();
    }

}
