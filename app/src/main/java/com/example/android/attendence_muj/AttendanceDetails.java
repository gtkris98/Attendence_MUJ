package com.example.android.attendence_muj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AttendanceDetails extends AppCompatActivity
{
    DatabaseHandler databaseHandler=new DatabaseHandler(this,1);
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    ListView listView;
    List<String> datelist = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendence_details);

        listView = (ListView)findViewById(R.id.DateList);
        tv1=(TextView)findViewById(R.id.tv_roll);
        tv2=(TextView)findViewById(R.id.tv_name);
        tv3=(TextView)findViewById(R.id.tv_total_class);
        tv4=(TextView)findViewById(R.id.tv_present);
        tv5=(TextView)findViewById(R.id.tv_absent);
        tv6=(TextView)findViewById(R.id.tv_percentage);


        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String id=bundle.getString("id");
        datelist.addAll(databaseHandler.getDateWise(Integer.parseInt(id.trim())));
        ArrayAdapter<String> list=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,datelist);
        listView.setAdapter(list);
        Attendence attendence=databaseHandler.getAttendence(id);
        Student student=databaseHandler.getStudent(id);
        tv1.setText(String.valueOf(student.getId()));
        tv2.setText(String.valueOf(student.getName()));
        tv3.setText(String.valueOf(attendence.getTotal_class()));
        tv4.setText(String.valueOf(attendence.getPresent()));
        tv5.setText(String.valueOf(attendence.getAbsent()));
        if (attendence.getPercentage().toString().length() > 4)
        {
            tv6.setText(attendence.getPercentage().toString().substring(0,4)+"%");
        }
        else
        {
            tv6.setText(attendence.getPercentage().toString() + " %");
        }
    }

}
