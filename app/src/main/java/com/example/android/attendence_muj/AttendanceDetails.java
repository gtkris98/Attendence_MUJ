package com.example.android.attendence_muj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AttendanceDetails extends AppCompatActivity
{
    DatabaseHandler databaseHandler=new DatabaseHandler(this,1);
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendence_details);
        tv1=(TextView)findViewById(R.id.tv_roll);
        tv2=(TextView)findViewById(R.id.tv_name);
        tv3=(TextView)findViewById(R.id.tv_total_class);
        tv4=(TextView)findViewById(R.id.tv_present);
        tv5=(TextView)findViewById(R.id.tv_absent);
        tv6=(TextView)findViewById(R.id.tv_percentage);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String id=bundle.getString("id");
        Attendence attendence=databaseHandler.getAttendence(id);
        Student student=databaseHandler.getStudent(id);
        tv1.setText(String.valueOf(student.getId()));
        tv2.setText(String.valueOf(student.getName()));
        tv3.setText(String.valueOf(attendence.getTotal_class()));
        tv4.setText(String.valueOf(attendence.getPresent()));
        tv5.setText(String.valueOf(attendence.getAbsent()));
        tv6.setText(attendence.getPercentage().toString().substring(0,4)+" %");
    }

}
