package com.example.android.attendence_muj;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TakeAttendence extends AppCompatActivity
{
    int date;
    String reg_no;
    String details_of_student;
    private DatabaseHandler databaseHandler=new DatabaseHandler(this,1);
    TextView tv1;//tv2;
    DatePicker dp;
    Button present,absent;
    List<String> names=new ArrayList<>();
    Iterator<String> iterator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_attendence);
        tv1=(TextView)findViewById(R.id.tv_regid);
        //tv2=(TextView)findViewById(R.id.tv_name);
        dp=(DatePicker)findViewById(R.id.date);
        present=(Button)findViewById(R.id.present);
        absent=(Button)findViewById(R.id.absent);
        names.addAll(databaseHandler.getAllNamesAndPercentage());
        iterator=names.iterator();
        if (!iterator.hasNext())
        {
            Toast.makeText(this,"Add Students First",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this,menu.class);
            startActivity(i);
        }
        else
        {
            details_of_student=iterator.next();
            tv1.setText(details_of_student);
        }

        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Log.d("Present","Clicked");
                int date = dp.getYear()*10000;
                date += dp.getMonth()*100;
                date += dp.getDayOfMonth();
                if(iterator.hasNext())
                {
                    reg_no=details_of_student.substring(0,2);
                    Log.d("Value of Reg_no :"," "+reg_no);
                    databaseHandler.addPresent(reg_no);
                    details_of_student=iterator.next();
                    tv1.setText(details_of_student);
                    DateWise dateWise = new DateWise();
                    dateWise.setAttendence(1);
                    dateWise.setDate(date);
                    dateWise.setId(Integer.parseInt(reg_no.trim()));
                    databaseHandler.addDateWise(dateWise);
                }
                else
                {
                    reg_no=details_of_student.substring(0,2);
                    Log.d("Value of Reg_no :"," "+reg_no);
                    databaseHandler.addPresent(reg_no);
                    DateWise dateWise = new DateWise();
                    dateWise.setAttendence(1);
                    dateWise.setDate(date);
                    dateWise.setId(Integer.parseInt(reg_no.trim()));
                    databaseHandler.addDateWise(dateWise);
                    //above code to add present for last student
                    iterator=names.iterator();
                    tv1.setText(iterator.next());
                    Toast.makeText(TakeAttendence.this, "Attendence Successfully Completed", Toast.LENGTH_LONG).show();
                    Intent i=new Intent(TakeAttendence.this,menu.class);
                    finish();
                    startActivity(i);


                }
            }
        });
        absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int date = dp.getYear()*10000;
                date += dp.getMonth()*100;
                date += dp.getDayOfMonth();
                if(iterator.hasNext())
                {
                    reg_no=details_of_student.substring(0,2);
                    Log.d("Value of Reg_no :"," "+reg_no);
                    databaseHandler.addAbsent(reg_no);
                    details_of_student=iterator.next();
                    tv1.setText(details_of_student);
                    DateWise dateWise = new DateWise();
                    dateWise.setAttendence(0);
                    dateWise.setDate(date);
                    dateWise.setId(Integer.parseInt(reg_no.trim()));
                    databaseHandler.addDateWise(dateWise);
                }
                else
                {
                    //details_of_student=iterator.next();
                    //tv1.setText(details_of_student);
                    reg_no=details_of_student.substring(0,2);
                    Log.d("Value of Reg_no :"," "+reg_no);
                    databaseHandler.addAbsent(reg_no);
                    DateWise dateWise = new DateWise();
                    dateWise.setAttendence(0);
                    dateWise.setDate(date);
                    dateWise.setId(Integer.parseInt(reg_no.trim()));
                    databaseHandler.addDateWise(dateWise);
                    //above code to add absent for last student
                    iterator=names.iterator();
                    tv1.setText(iterator.next());
                    Toast.makeText(TakeAttendence.this, "Attendence Successfully Completed", Toast.LENGTH_LONG).show();
                    Intent i=new Intent(TakeAttendence.this,menu.class);
                    finish();
                    startActivity(i);
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,menu.class);
        finish();
        startActivity(i);
    }
}
