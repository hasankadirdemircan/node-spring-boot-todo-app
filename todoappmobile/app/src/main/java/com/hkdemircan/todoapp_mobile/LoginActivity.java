package com.hkdemircan.todoapp_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hkdemircan.todoapp_mobile.model.User;
import com.hkdemircan.todoapp_mobile.model.UserCreate;
import com.hkdemircan.todoapp_mobile.restapi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView registerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        define();
        registerListener();
    }

    private void define(){
        registerTextView = findViewById(R.id.registerTextView);
    }

    private void registerListener(){
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(ıntent);
            }
        });
    }

}
