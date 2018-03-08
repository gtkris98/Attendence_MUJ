package com.example.android.attendence_muj;

public class Attendence
{
    int id,total_class,present,absent;
    Double percentage=0.00;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal_class() {
        return total_class;
    }

    public void setTotal_class(int total_class) {
        this.total_class = total_class;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
