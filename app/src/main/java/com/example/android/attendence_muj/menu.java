package com.example.android.attendence_muj;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class menu extends AppCompatActivity
{
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Button attendence= (Button)findViewById(R.id.Attendence);
        Button student=(Button)findViewById(R.id.Student);
        attendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(menu.this,TakeAttendence.class);
                finish();
                startActivity(i);

            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(menu.this,StudentDetails.class);
                finish();
                startActivity(i);

            }
        });
    }
    //code to implement double back press to exit functionality
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();//this exits the activity just like finish() function
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to LOGOUT", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
