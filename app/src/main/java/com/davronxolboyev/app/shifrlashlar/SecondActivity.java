package com.davronxolboyev.app.shifrlashlar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.davronxolboyev.app.shifrlashlar.model.Methods;
import com.davronxolboyev.app.shifrlashlar.model.MethodsAdapter;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    List<Methods> methods;
    MethodsAdapter adapter;
    RecyclerView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        methods = new ArrayList<>();

        methods.add(new Methods(1,"Sezar usuli"));
        methods.add(new Methods(2,"Vijiner usuli"));
        methods.add(new Methods(3,"RSA usuli"));
        methods.add(new Methods(4,"Polibiya usuli"));
        methods.add(new Methods(5,"Affian usuli"));
        methods.add(new Methods(6,"El Gamal usuli"));
        methods.add(new Methods(7,"Gamilton usuli"));

        startRecyclerView(methods);
    }

    private void startRecyclerView(List<Methods> methodslist) {

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        view = findViewById(R.id.recyclerView);
        view.setLayoutManager(manager);
        adapter = new MethodsAdapter(this,methodslist);
        view.setAdapter(adapter);
    }
}