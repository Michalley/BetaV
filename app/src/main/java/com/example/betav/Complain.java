package com.example.betav;

public class Complain {

    public String user;
    public String date;
    public String time;
    public String category;
    public String zone;
    public int emergency;
    public int state;
    public String notes;
    public String name;
    public String pic;

    public Complain (){}

    public Complain (String user,String date,String time,String category,String zone,int emergency,int state,String notes,String name,String pic){
        this.category=category;
        this.date=date;
        this.emergency=emergency;
        this.name=name;
        this.zone=zone;
        this.user=user;
        this.time=time;
        this.state=state;
        this.notes=notes;
        this.pic=pic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public int getEmergency() {
        return emergency;
    }

    public int getState() {
        return state;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public String getTime() {
        return time;
    }

    public String getUser() {
        return user;
    }

    public String getZone() {
        return zone;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEmergency(int emergency) {
        this.emergency = emergency;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }


}
