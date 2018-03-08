package com.example.android.attendence_muj;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Welcome extends AppCompatActivity
{
    boolean doubleBackToExitPressedOnce = false;
    EditText et1;
    EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        et1=(EditText)findViewById(R.id.login_id);
        et2=(EditText)findViewById(R.id.password);
        Button login=(Button)findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (String.valueOf(et1.getText()).equals("") || String.valueOf(et2.getText()).equals("") )
                {
                    Toast.makeText(Welcome.this, "Login ID/Password Cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if (String.valueOf(et1.getText()).equals("devesh") && String.valueOf(et2.getText()).equals("manipal123"))
                {
                    Intent intent = new Intent(Welcome.this,menu.class);
                    startActivity(intent);
                }
                else if (String.valueOf(et2.getText()).equals("gupta123"))
                {

                    Intent intent=new Intent(Welcome.this,AttendanceDetails.class);
                    Bundle b=new Bundle();
                    String a= String.valueOf(et1.getText());
                    Log.d("Value of a",""+a);
                    b.putString("id",a.substring(0,1));
                    intent.putExtras(b);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Welcome.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                }

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
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
