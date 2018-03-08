package com.example.android.attendence_muj;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.animation.AnimatorUpdateListenerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentDetails extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private DatabaseHandler databaseHandler=new DatabaseHandler(this,1);

    private List<String> names = new ArrayList<>();//Array List to store names
    private ListView name_list; //Layout Resource to display the above ArrayLists
    EditText et1,et2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_details); //Set layout Student_details
        name_list=(ListView)findViewById(R.id.NameList);
        names.addAll(databaseHandler.getAllNamesAndPercentage());
        ArrayAdapter<String> list=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,names);
        name_list.setAdapter(list);
        et1=(EditText)findViewById(R.id.et_regid);
        et2=(EditText)findViewById(R.id.et_name);
        Button add=(Button)findViewById(R.id.add);
        name_list.setOnItemClickListener(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(et1.getText().toString().equalsIgnoreCase("") || et2.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(StudentDetails.this,"Fields Cannot be Blank",Toast.LENGTH_SHORT);
                }
                else
                {
                    Student s = new Student();
                    int i = Integer.parseInt(et1.getText().toString());
                    String n = et2.getText().toString();
                    s.setId(i);
                    s.setName(n);
                    databaseHandler.addStudent(s);
                    Toast.makeText(StudentDetails.this, "Student Added Successfully", Toast.LENGTH_SHORT).show();
                    UpdateArray();
                }
            }
        });
    }
    public void UpdateArray()
    {
        names.clear();
        names.addAll(databaseHandler.getAllNamesAndPercentage());
        ArrayAdapter<String> list=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,names);
        name_list.setAdapter(list);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Intent intent=new Intent(this,AttendanceDetails.class);
        Bundle b=new Bundle();
        String a= names.get(i);
        Log.d("Value of a",""+a);
        b.putString("id",a.substring(0,3));
        intent.putExtras(b);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,menu.class);
        finish();
        startActivity(i);
    }
}
