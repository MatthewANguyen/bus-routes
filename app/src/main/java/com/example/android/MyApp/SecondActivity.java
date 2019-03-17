package com.example.android.MyApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import static java.lang.Integer.parseInt;

public class SecondActivity extends AppCompatActivity {
    private Button save, cancel;
    private EditText username, password, passwordReenter, firstName, lastName, email, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_activity);
        username = findViewById(R.id.editUsername);
        password = findViewById(R.id.editPasswordEntry);
        passwordReenter = findViewById(R.id.editPasswordReentry);
        firstName = findViewById(R.id.editFirstName);
        lastName = findViewById(R.id.editLastName);
        email = findViewById(R.id.editEmail);
        age = findViewById(R.id.editAge);

        save = findViewById(R.id.btnSave);
        cancel = findViewById(R.id.btnCancel);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernameInput, passwordInput, passwordReenterInput, firstNameInput, lastNameInput, emailInput;
                int ageInput = 0;

                usernameInput = username.getText().toString();
                passwordInput = password.getText().toString();
                passwordReenterInput = passwordReenter.getText().toString();
                firstNameInput = firstName.getText().toString();
                lastNameInput = lastName.getText().toString();
                emailInput = email.getText().toString();
                if(!age.getText().toString().isEmpty()){
                    ageInput = parseInt(age.getText().toString());
                }

                if(usernameInput.isEmpty() || passwordInput.isEmpty() || passwordReenterInput.isEmpty() ||
                        firstNameInput.isEmpty() || lastNameInput.isEmpty() || emailInput.isEmpty() || ageInput > 99 || ageInput < 1) {
                    Toast.makeText(SecondActivity.this, R.string.registration_failed, Toast.LENGTH_LONG).show();
                } else if(!passwordInput.equals(passwordReenterInput)) {
                    Toast.makeText(SecondActivity.this, R.string.registration_failed, Toast.LENGTH_LONG).show();
                } else {
                    String credentials = "username:" + usernameInput +
                            "\npassword:" + passwordInput +
                            "\nfirst:" + firstNameInput +
                            "\nlast:" + lastNameInput +
                            "\nemail:" + emailInput +
                            "\nage:" + String.valueOf(ageInput) + ",";
                    write(credentials);
                    Toast.makeText(SecondActivity.this, "works", Toast.LENGTH_LONG).show();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    public void write(String credentials) {
        // add-write text into file
        try {
            FileOutputStream fileOut=openFileOutput("mytextfile.txt", MODE_APPEND);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileOut);
            outputWriter.append(credentials);
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
