package com.example.android.MyApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    private Button login, newUser;
    private int attempts;
    private static int READ_BLOCK_SIZE = 100;

    private static final String TAG = "Hello World";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "OnCreate Called!");
        setContentView(R.layout.activity_main);

        attempts = 3;

        username = findViewById(R.id.editName);

        password = findViewById(R.id.editPassword);

        login = findViewById(R.id.btnLogin);

        newUser = findViewById(R.id.btnNewUser);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean usernamePass = false, passwordPass = false;
                String usernameCheck = username.getText().toString();
                String passwordCheck = password.getText().toString();

                //Remove later - testing
//                Intent i = new Intent(MainActivity.this, RouteActivity.class);
//                startActivity(i);

                //Add again later

               if(true) {
                   try {
                       FileInputStream fileIn=openFileInput("mytextfile.txt");
                       InputStreamReader InputRead= new InputStreamReader(fileIn);
                       char[] inputBuffer= new char[READ_BLOCK_SIZE];
                       String s="";
                       int charRead;

                       while ((charRead=InputRead.read(inputBuffer))>0) {
                           // char to string conversion
                           String readstring=String.copyValueOf(inputBuffer,0, charRead);
                           s +=readstring;
                       }
                       String[] full = s.split(",");
                       String[] part = {};
                       int accounts = full.length;
                       for(int i = 0; i < accounts; i++) {
                           part = full[i].split("\\r?\\n");
                           if(part[0].split(":")[1].equals(usernameCheck)) {
                               if(part[1].split(":")[1].equals(passwordCheck)){
                                   usernamePass = true;
                                   passwordPass = true;
                               }
                           }
                       }
                       if(usernamePass && passwordPass) {
                           Toast.makeText(getBaseContext(), R.string.login_success, Toast.LENGTH_SHORT).show();
                           Intent i = new Intent(MainActivity.this, RouteActivity.class);
                           startActivity(i);
                       } else {
                           Toast.makeText(getBaseContext(), R.string.login_failed, Toast.LENGTH_SHORT).show();
                       }
                   } catch (FileNotFoundException e) {
                       e.printStackTrace();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               } else {
                   Toast.makeText(MainActivity.this, R.string.incorrect, Toast.LENGTH_LONG).show();
                   attempts--;
                   if(attempts == 0) {
                       android.os.Process.killProcess(android.os.Process.myPid());
                       System.exit(1);
                   }
               }
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
    }
}
