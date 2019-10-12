package com.example.lab3_sharedpreferences;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText,emailEditText,passwordEditText,dateEditText;
    private CheckBox checkbox;
    private SharedPreferences sharedPreferences;
    private Button loginButton,dateButton;
    private Calendar calendar ;
    private DatePickerDialog datePickerDialog;
    int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("Name", nameEditText.getText().toString());
                    editor.putString("Email", emailEditText.getText().toString());
                    editor.putString("Password", passwordEditText.getText().toString());
                    editor.commit();
                    Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(MainActivity.this, "Not saved!", Toast.LENGTH_LONG).show();
                }

            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateEditText.setText(day+"/"+month+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });



        getSharedPreferenceStrings("Name","Email","Password");

    }

    private void initialize()
    {
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        checkbox = findViewById(R.id.checkBox);
        loginButton = findViewById(R.id.loginButton);
        dateEditText = findViewById(R.id.dateEditText);
        dateButton = findViewById(R.id.dateButton);
    }

    private void getSharedPreferenceStrings(String nameKey,String emailKey,String passwordKey)
    {
        String name = sharedPreferences.getString(nameKey,"");
        if (name!="")
        {
            nameEditText.setText(name);
        }
        String email = sharedPreferences.getString(emailKey,"");
        if (email!="")
        {
            emailEditText.setText(email);
        }
        String password = sharedPreferences.getString(passwordKey,"");
        if (password!="")
        {
            passwordEditText.setText(password);
        }
    }
}
