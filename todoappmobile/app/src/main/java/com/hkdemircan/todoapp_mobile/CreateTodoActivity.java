package com.hkdemircan.todoapp_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CreateTodoActivity extends AppCompatActivity {

    EditText todoHeaderEditText, todoContentEditText;
    Button saveTodoButton;

    //session durumu
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);

        define();
    }

    private void define(){
        todoHeaderEditText = findViewById(R.id.todoHeaderEditText);
        todoContentEditText = findViewById(R.id.todoContentEditText);

        saveTodoButton = findViewById(R.id.saveTodoButton);

        // Session Manager tanımladık.
        session = new SessionManager(getApplicationContext());
    }
}
