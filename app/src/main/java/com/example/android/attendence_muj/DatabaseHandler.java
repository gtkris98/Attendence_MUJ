package com.example.android.attendence_muj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper
{
    public static String name="AttendenceITB";
    public static int Version=1;

    public DatabaseHandler(Context context, int version)
    {
        super(context,name,null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String query="Create table student (id integer primary key,name text )";
        sqLiteDatabase.execSQL(query);
        Log.d("FirstQuery","Worked");
        query="Create table attendence (id integer primary key,total_class float, present float, absent float, percentage float )";
        sqLiteDatabase.execSQL(query);
        Log.d("SecondQuery","Worked");
        query="Create table date (id integer, day integer, present integer )";
        sqLiteDatabase.execSQL(query);
        Log.d("ThirdQuery","Worked");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        String query="drop table student";
        sqLiteDatabase.execSQL(query);
        query="drop table attendence";
        sqLiteDatabase.execSQL(query);
        query="drop table date";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);

    }

    public void addStudent(Student student)
    {
        Log.d("Worked","Addfunction");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",student.getId());
        values.put("name",student.getName());
        db.insert("student", null, values);
        Attendence a=new Attendence();
        a.setId(student.getId());
        a.setTotal_class(0);
        a.setPresent(0);
        a.setAbsent(0);
        a.setPercentage(0.00);
        addAttendence(a);
        db.close();

    }

    public void addPresent(String reg_no)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        String query="UPDATE attendence SET present = present+1, total_class = total_class+1 WHERE id = "+reg_no;
        db.execSQL(query);
        query="UPDATE attendence SET percentage = ((present/total_class)*100) WHERE id = "+reg_no;
        db.execSQL(query);
    }
    public void addAbsent(String reg_no)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        String query="UPDATE attendence SET absent = absent+1, total_class = total_class+1 WHERE id = "+reg_no;
        db.execSQL(query);
        query="UPDATE attendence SET percentage = ((present/total_class)*100) WHERE id = "+reg_no;
        db.execSQL(query);
    }

    public void addDateWise(DateWise dateWise)
    {
        Log.d("Worked","AddDateWise");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",dateWise.getId());
        values.put("day",dateWise.getDate());
        values.put("present",dateWise.getAttendence());
        db.insert("date", null, values);
        db.close();
    }

    public void addAttendence(Attendence attendence)
    {
        Log.d("Worked","AddAttendence");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",attendence.getId());
        values.put("total_class",attendence.getTotal_class());
        values.put("present",attendence.getPresent());
        values.put("absent",attendence.getAbsent());
        values.put("percentage",attendence.getPercentage());
        db.insert("attendence", null, values);
        db.close();
    }

    public Attendence getAttendence(String id)
    {
        Attendence a =new Attendence();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from attendence where id = "+id,null);
        if(cursor.moveToFirst())
        {
            do
            {
                a.setId(cursor.getInt(0));
                a.setTotal_class(cursor.getInt(cursor.getColumnIndex("total_class")));
                a.setPresent(cursor.getInt(2));
                a.setAbsent(cursor.getInt(3));
                a.setPercentage(cursor.getDouble(4));
            }while (cursor.moveToNext());
        }
        return a;
    }

    public Student getStudent(String id)
    {
        Student s =new Student();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from Student where id = "+id,null);
        if(cursor.moveToFirst())
        {
            do
            {
                s.setId(cursor.getInt(0));
                s.setName(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        return s;
    }

     public  int StudentNumber()
    {
        int no=0;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select id from student",null);
        if (cursor.moveToFirst()) {
            do
            {
                no++;
            } while (cursor.moveToNext());
        }
        return no;
    }

    public List<String> getAllNamesAndPercentage()
    {
        List<String> StudentNames=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select id, name FROM student",null);
        if (cursor.moveToFirst()) {
            do
            {
                StudentNames.add(cursor.getInt(cursor.getColumnIndex("id")) + "\t\t" +cursor.getString(cursor.getColumnIndex("name")));
            } while (cursor.moveToNext());
        }
        return StudentNames;
    }



    public List<String> getDateWise(int reg_no)
    {
        List<String> datewiseAttendance = new ArrayList<>();
        String attendance,dates,formatted_date;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select day, present FROM date where id = "+reg_no,null);
        if (cursor.moveToFirst())
        {
            do
            {
                dates = String.valueOf(cursor.getInt(cursor.getColumnIndex("day")));
                Log.d("dates","  "+dates);
                formatted_date = dates.substring(6) + "-" + dates.substring(4,6) + "-" + dates.substring(0,4);
                if (cursor.getInt(cursor.getColumnIndex("present")) == 0)
                {
                    datewiseAttendance.add(formatted_date + "\t\t\t\t\t" + "Absent");
                }
                else
                {
                    datewiseAttendance.add(formatted_date + "\t\t\t\t\t" + "Present");
                }

            }while (cursor.moveToNext());
        }
        return datewiseAttendance;
    }
}
