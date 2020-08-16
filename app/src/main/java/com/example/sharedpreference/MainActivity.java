package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private EditText usernameEditText,passwordEditText;
private Button saveButton,loadButton;
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText=findViewById(R.id.usernameId);
        passwordEditText=findViewById(R.id.passwordId);
        saveButton=findViewById(R.id.saveId);
        loadButton=findViewById(R.id.loadId);
        textView=findViewById(R.id.textviewId);

        saveButton.setOnClickListener(this);
        loadButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.saveId){

            String UserName = usernameEditText.getText().toString();
            String Password= passwordEditText.getText().toString();

            if (UserName.equals("") && Password.equals(""))
            {
                Toast.makeText(MainActivity.this,"please enter info for store",Toast.LENGTH_SHORT).show();
            }else
            {
                SharedPreferences sharedPreferences=getSharedPreferences("userdetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usernamekey",UserName);
                editor.putString("passwordkey",Password);
                editor.commit();
                usernameEditText.setText("");
                passwordEditText.setText("");
                Toast.makeText(MainActivity.this,"Data is stored successfully",Toast.LENGTH_SHORT).show();

            }

        }else if (v.getId()==R.id.loadId){
            SharedPreferences sharedPreferences=getSharedPreferences("userdetails", Context.MODE_PRIVATE);

            if (sharedPreferences.contains("usernamekey") && sharedPreferences.contains("passwordkey"))
            {
                String Username =sharedPreferences.getString("usernamekey","data not found");
                String Password =sharedPreferences.getString("passwordkey","data not found");
                textView.setText(Username+"\n"+Password);
            }

        }
    }
}