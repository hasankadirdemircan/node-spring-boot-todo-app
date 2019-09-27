package com.hkdemircan.todoapp_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton actionNewTodo, actionLogout;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        define();
        floatingActionButtonListener();
    }

    private void floatingActionButtonListener(){
        actionLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
                Intent ıntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(ıntent);
            }
        });

        actionNewTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(MainActivity.this, CreateTodoActivity.class);
                startActivity(ıntent);
            }
        });
    }
    private void define(){
        session = new SessionManager(getApplicationContext());

        actionNewTodo = findViewById(R.id.actionNewTodo);
        actionLogout = findViewById(R.id.actionLogout);
    }
}
