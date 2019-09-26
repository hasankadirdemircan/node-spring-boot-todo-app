package com.hkdemircan.todoapp_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hkdemircan.todoapp_mobile.model.User;
import com.hkdemircan.todoapp_mobile.model.UserCreate;
import com.hkdemircan.todoapp_mobile.restapi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText registerUsernameEditText, registerPasswordEditText, registerConfirmPasswordEditText;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        define();
        register();
    }

    private void define(){
        registerUsernameEditText = findViewById(R.id.registerUsernameEditText);
        registerPasswordEditText = findViewById(R.id.registerPasswordEditText);
        registerConfirmPasswordEditText = findViewById(R.id.registerConfirmPasswordEditText);

        registerButton = findViewById(R.id.registerButton);
    }

    private void register(){
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPassword(registerPasswordEditText.getText().toString().trim(), registerConfirmPasswordEditText.getText().toString().trim())){
                    UserCreate userCreate = new UserCreate();
                    User user = new User();
                    user.setUsername(registerUsernameEditText.getText().toString().trim());
                    user.setPassword(registerPasswordEditText.getText().toString().trim());
                    user.setRole("admin");
                    userCreate.setUser(user);
                    Call<Void> registerUserCall = ManagerAll.getInstance().registerUser(userCreate);
                    registerUserCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Kayıt Başarılı", Toast.LENGTH_LONG).show();
                                Intent ıntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(ıntent);
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Opps.. Kayıt Başarısız :(", Toast.LENGTH_LONG).show();
                        }
                    });

                }else{
                    Toast.makeText(getApplicationContext(), "Şifreler Uyuşmuyor", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean checkPassword(String password, String confirmPassword){
        return ((password.equals(confirmPassword)) ? true : false);
    }

}
