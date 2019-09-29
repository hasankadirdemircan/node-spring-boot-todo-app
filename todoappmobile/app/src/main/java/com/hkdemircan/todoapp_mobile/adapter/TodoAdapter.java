package com.hkdemircan.todoapp_mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hkdemircan.todoapp_mobile.R;

import com.hkdemircan.todoapp_mobile.model.GetTodo;

import org.w3c.dom.Text;

public class TodoAdapter extends BaseAdapter {

    GetTodo todoList;
    Context context;

    public TodoAdapter(GetTodo todoList, Context context) {
        this.todoList = todoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return todoList.getTodos().size();
    }

    @Override
    public Object getItem(int position) {
        return todoList.getTodos().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.todo_layout, parent, false);
        TextView todoHeaderTextView = convertView.findViewById(R.id.todoHeaderTextView);
        TextView todoDateTextView = convertView.findViewById(R.id.todoDateTextView);

        todoHeaderTextView.setText(todoList.getTodos().get(position).getHeader());
        todoDateTextView.setText(todoList.getTodos().get(position).getCreateDate());

        return convertView;
    }
}
