package com.example.betav;

public class User {

    public String name;
    public String lName;
    public String email;
    public String phone;
    public int level;
    public String uid;

    public User () { }

    public User (String name,String lName,String email,String phone,int level,String uid){
        this.name=name;
        this.lName=lName;
        this.email=email;
        this.phone=phone;
        this.level=level;
        this.uid=uid;
    }

    public String getName(){
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getEmail() {
        return email;
    }

    public String getlName() {
        return lName;
    }

    public String getUid() {
        return uid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
