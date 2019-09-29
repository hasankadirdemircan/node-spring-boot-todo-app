package com.hkdemircan.todoapp_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.hkdemircan.todoapp_mobile.adapter.TodoAdapter;
import com.hkdemircan.todoapp_mobile.model.GetTodo;
import com.hkdemircan.todoapp_mobile.restapi.ManagerAll;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton actionNewTodo, actionLogout;
    SessionManager session;
    ListView todoListView;
    GetTodo getTodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        define();
        getTodo();
        floatingActionButtonListener();
        todoListViewClickListener();

    }

    private void todoListViewClickListener(){
       todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, TodoDetailActivity.class);
                intent.putExtra("id", getTodo.getTodos().get(position).getId());
                startActivity(intent);
           }
       });
    }

    private void getTodo(){
        Call<GetTodo> getTodoCall = ManagerAll.getInstance().getTodo(getToken());
        getTodoCall.enqueue(new Callback<GetTodo>() {
            @Override
            public void onResponse(Call<GetTodo> call, Response<GetTodo> response) {
                if(response.isSuccessful()){
                    getTodo = response.body();
                    TodoAdapter todoAdapter = new TodoAdapter(getTodo, getApplicationContext());
                    todoListView.setAdapter(todoAdapter);
                }
            }

            @Override
            public void onFailure(Call<GetTodo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Todo'lar Çekilirken Hata", Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getToken(){
        HashMap<String, String> user = session.getUserDetails();
        //session token and username
        return user.get((SessionManager.KEY_TOKEN));
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

        todoListView = findViewById(R.id.todoListView);
    }
}
