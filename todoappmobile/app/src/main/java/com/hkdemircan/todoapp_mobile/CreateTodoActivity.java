package com.hkdemircan.todoapp_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hkdemircan.todoapp_mobile.model.Todo;
import com.hkdemircan.todoapp_mobile.model.TodoCreate;
import com.hkdemircan.todoapp_mobile.restapi.ManagerAll;
import com.hkdemircan.todoapp_mobile.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTodoActivity extends AppCompatActivity {

    EditText todoHeaderEditText, todoContentEditText;
    Button saveTodoButton;
    Spinner priortySpinner;
    ArrayAdapter<String> priortySpinnerAdapter;
    String priortyString;

    //session durumu
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);

        define();
        priortySpinnerProcess();
        saveTodoButtonListener();
    }

    private void getTodo(){

    }
    private void saveTodoButtonListener(){
        saveTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String todoHeader = todoHeaderEditText.getText().toString();
               String todoContent = todoContentEditText.getText().toString();
               if(!Util.isNullOrEmpty(todoHeader) && !Util.isNullOrEmpty(todoContent)){
                   HashMap<String, String> user = session.getUserDetails();
                   //session token and username
                   String token = user.get((SessionManager.KEY_TOKEN));
                   String username = user.get((SessionManager.KEY_EMAIL));
                   TodoCreate todoCreate = new TodoCreate();
                   Todo todo = new Todo();
                   todo.setActive("X");
                   todo.setHeader(todoHeader);
                   todo.setTodo(todoContent);
                   todo.setUsername(username);
                   todoCreate.setTodo(todo);
                   Call<Void> saveTodoCall = ManagerAll.getInstance().saveTodo(token, todoCreate);
                    saveTodoCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Kayıt Başarılı", Toast.LENGTH_LONG).show();
                                Intent ıntent = new Intent(CreateTodoActivity.this, MainActivity.class);
                                startActivity(ıntent);
                            }else{
                                Toast.makeText(getApplicationContext(), "Kayıt Başarısız", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Sistemsel Hata", Toast.LENGTH_LONG).show();
                        }
                    });
               }else{
                   Toast.makeText(getApplicationContext(), "Lütfen Eksiksiz Doldurunuz", Toast.LENGTH_LONG).show();
               }
            }
        });
    }

    private void define(){
        todoHeaderEditText = findViewById(R.id.todoHeaderEditText);
        todoContentEditText = findViewById(R.id.todoContentEditText);

        priortySpinner = findViewById(R.id.priortySpinner);

        saveTodoButton = findViewById(R.id.saveTodoButton);

        // Session Manager tanımladık.
        session = new SessionManager(getApplicationContext());
    }

    /*
    selected priorty spinner
     */
    private void priortySpinnerProcess(){
        // Initializing a String Array
        String[] priortyArray = new String[]{
                "1",
                "2",
                "3"
        };
        List<String> priortyList = new ArrayList<>(Arrays.asList(priortyArray));


        priortySpinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, priortyList);
        priortySpinner.setAdapter(priortySpinnerAdapter);

        priortySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                priortyString = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
