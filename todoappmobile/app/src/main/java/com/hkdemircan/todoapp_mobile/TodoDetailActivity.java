package com.hkdemircan.todoapp_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hkdemircan.todoapp_mobile.model.GetOneTodo;
import com.hkdemircan.todoapp_mobile.restapi.ManagerAll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoDetailActivity extends AppCompatActivity {

    Bundle bundle; //gelen id'yi intent'den almak icin
    EditText todoHeaderEditText, todoContentEditText;
    TextView detailDateTextView;
    Button updateTodoButton, deleteTodoButton;
    //session durumu
    SessionManager session;
    Spinner priortyDetailSpinner;
    ArrayAdapter<String> priortySpinnerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);

        define();
        priortySpinnerProcess();
        getTodo();
    }

    private void getTodo(){
        bundle = getIntent().getExtras();
        Call<GetOneTodo> getTodoCall = ManagerAll.getInstance().getTodoForId(getToken(), bundle.getInt("id"));
        getTodoCall.enqueue(new Callback<GetOneTodo>() {
            @Override
            public void onResponse(Call<GetOneTodo> call, Response<GetOneTodo> response) {
                if (response.isSuccessful()){
                    GetOneTodo getOneTodo = new GetOneTodo();
                    getOneTodo = response.body();
                    todoHeaderEditText.setText(getOneTodo.getTodo().getHeader());
                    todoContentEditText.setText(getOneTodo.getTodo().getTodo());
                    detailDateTextView.setText(getOneTodo.getTodo().getCreateDate());
                }
            }

            @Override
            public void onFailure(Call<GetOneTodo> call, Throwable t) {

            }
        });
    }

    private void define(){
        todoHeaderEditText = findViewById(R.id.todoHeaderEditText);
        todoContentEditText = findViewById(R.id.todoContentEditText);

        detailDateTextView = findViewById(R.id.detailDateTextView);

        updateTodoButton = findViewById(R.id.updateTodoButton);
        deleteTodoButton = findViewById(R.id.deleteTodoButton);

        priortyDetailSpinner = findViewById(R.id.priortyDetailSpinner);

        session = new SessionManager(getApplicationContext());
    }


    private String getToken(){
        HashMap<String, String> user = session.getUserDetails();
        //session token and username
        return user.get((SessionManager.KEY_TOKEN));
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
        priortyDetailSpinner.setAdapter(priortySpinnerAdapter);

        priortyDetailSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //priortyString = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
