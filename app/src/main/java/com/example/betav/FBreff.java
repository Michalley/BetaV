package com.example.betav;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FBreff {

    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();

    public static DatabaseReference refUsers = FBDB.getReference("Users");

    public static DatabaseReference refCat = FBDB.getReference("Categories");

    public static DatabaseReference refAre = FBDB.getReference("Area");

    public static DatabaseReference refCom = FBDB.getReference("Complain");

    public static DatabaseReference refHis = FBDB.getReference("History");

    public static StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();

    public static StorageReference ref = mStorageRef.child("images/pic.jpg");
}
