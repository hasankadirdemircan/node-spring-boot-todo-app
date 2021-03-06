package com.hkdemircan.todoapp_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hkdemircan.todoapp_mobile.model.LoginUser;
import com.hkdemircan.todoapp_mobile.model.User;
import com.hkdemircan.todoapp_mobile.model.UserCreate;
import com.hkdemircan.todoapp_mobile.restapi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView registerTextView;
    EditText usernameEditText, passwordEditText;
    Button loginButton;

    //session durumu
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        define();
        if(sessionControll()){
            Intent ıntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(ıntent);
            return;
        }
        registerListener();
        loginButtonClickListener();
    }

    private boolean sessionControll(){
        if(session.isLoggedIn()){
            String asd ="";
        }
        return ((session.isLoggedIn()) ? true : false);
    }
    private void define(){
        registerTextView = findViewById(R.id.registerTextView);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginButton = findViewById(R.id.loginButton);

        // Session Manager tanımladık.
        session = new SessionManager(getApplicationContext());
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

    private void loginButtonClickListener(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEmptyEditText(usernameEditText.getText().toString().trim(), passwordEditText.getText().toString().trim())){
                    final String username = usernameEditText.getText().toString().trim();
                    final String password = passwordEditText.getText().toString().trim();
                    LoginUser loginUser = new LoginUser(username, password);
                    Call<Void> loginUserCall = ManagerAll.getInstance().loginUser(loginUser);
                    loginUserCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()){
                                String token = response.headers().get("Authorization");
                                Toast.makeText(getApplicationContext(),"Giriş Başarılı",Toast.LENGTH_LONG).show();
                                //giriş başarılı ise Session'a verilerimizi yolladık.
                                session.createLoginSession(username, password, token);
                                //sonraki sayfaya yönlendirdik.
                                Intent ıntent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(ıntent);

                            }else{
                                Toast.makeText(getApplicationContext(),"Bilgiler Yanlış",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Lütfen Alanları Boş Bırakmayınız.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean checkEmptyEditText(String username, String password){
        return (((username.isEmpty() || password.isEmpty())) ? false : true);
    }

}
