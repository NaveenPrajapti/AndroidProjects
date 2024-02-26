package com.example.sqlitedb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     EditText edt1,edt2,edt3;
     Button btn1,btn2,btn3,btn4,btn5;
    database g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1=findViewById(R.id.nameInput);
        edt2=findViewById(R.id.usernameInput);
        edt3=findViewById(R.id.passwordInput);
        btn1=findViewById(R.id.insertBtn);
        btn2=findViewById(R.id.deleteBtn);
        btn3=findViewById(R.id.viewtBtn);
        btn4=findViewById(R.id.udateBtn);
        btn5=findViewById(R.id.feedbackbtn);
         g=new database(this);
      //  SQLiteDatabase db=g.getReadableDatabase();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1=edt1.getText().toString();
                String username1=edt2.getText().toString();
                String password1=edt3.getText().toString();

                if(name1.equals("") || username1.equals("") || password1.equals("")){
                    Toast.makeText(MainActivity.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                     Boolean i=g.insertdata(name1,username1,password1);
                     if (i==true){
                         Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                     }
                     else {
                         Toast.makeText(MainActivity.this, "Not Successful", Toast.LENGTH_SHORT).show();
                     }

                }
                edt1.setText("");
                edt2.setText("");
                edt3.setText("");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edt2.getText().toString();
                Boolean i=g.deletedata(username);
                if (i==true){
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(MainActivity.this, "Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor t=g.getInfo();
                if (t.getCount()==0){
                    Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer=new StringBuffer();
                while (t.moveToNext()){
                    buffer.append("Name::"+t.getString(1)+"\n");
                    buffer.append("Username::"+t.getString(2)+"\n");
                    buffer.append("Password::"+t.getString(3)+"\n\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Sign Up Userdata");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edt1.getText().toString();
                String username=edt2.getText().toString();
                String password= edt3.getText().toString();
                Boolean i =g.updatedata(name,username,password);
                if (i==true){
                    Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Not Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Feedback.class);
                startActivity(intent);
            }
        });

    }
}