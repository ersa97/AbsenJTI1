package com.e.absenjti1.list;

import com.google.firebase.Timestamp;

public class attendance {

    String Id;
    String Name;
    String Location;
    Timestamp Date;

    public String getLocation(){
        return Location;
    }

    public void setLocation(String location){
        Location = location;
    }
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp date) {
        Date = date;
    }


    public attendance(String id, String name, String location, Timestamp date) {
        Id = id;
        Name = name;
        Date = date;
        Location = location;
    }

    public attendance(){

    }



}