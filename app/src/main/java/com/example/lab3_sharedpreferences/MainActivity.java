package com.example.lab3_sharedpreferences;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText,emailEditText,passwordEditText,dateEditText;
    private CheckBox checkbox;
    private SharedPreferences sharedPreferences;
    private Button loginButton,dateButton;
    private Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;
    private String dateOfPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        sharedPreferences = getSharedPreferences("User2", Context.MODE_PRIVATE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("Name", nameEditText.getText().toString());
                    editor.putString("Email", emailEditText.getText().toString());
                    editor.putString("Password", passwordEditText.getText().toString());
                    editor.putString("Date", dateOfPicker);
                    editor.commit();
                    Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(MainActivity.this, "Not saved!", Toast.LENGTH_LONG).show();
                }

            }
        });


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                Log.i("Date", String.valueOf(myCalendar.get(Calendar.DAY_OF_MONTH)));
                dateOfPicker = myCalendar.get(Calendar.YEAR)+"/"+(myCalendar.get(Calendar.MONTH)+1)+"/"+myCalendar.get(Calendar.DAY_OF_MONTH);
                dateEditText.setText(dateOfPicker);
            }

        };

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


        getSharedPreferenceStrings("Name","Email","Password", "Date");

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

    private void getSharedPreferenceStrings(String nameKey,String emailKey,String passwordKey, String dateKey)
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
        String date = sharedPreferences.getString(dateKey, "");
        if (date!=""){
            dateEditText.setText(date);
        }
    }
}
