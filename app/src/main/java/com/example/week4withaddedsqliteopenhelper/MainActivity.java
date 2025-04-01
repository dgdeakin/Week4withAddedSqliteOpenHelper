package com.example.week4withaddedsqliteopenhelper;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    RecyclerView recyclerView;

    String[] dataNames = {
            "Item 1",
            "Item 2",
            "Item 1",
            "Item 2",
            "Item 1",
            "Item 2",
            "Item 1",
            "Item 2"
    };

    //Adapter
//    MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, NameListFragment.class, null)
                .commit();

//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        ArrayList<NameDataModel> nameList = new ArrayList<>();
//        nameList.add(new NameDataModel("Alice"));
//        nameList.add(new NameDataModel("Bob"));
//        nameList.add(new NameDataModel("Charlie"));
//        nameList.add(new NameDataModel("Alice"));
//        nameList.add(new NameDataModel("Bob"));
//        nameList.add(new NameDataModel("Charlie"));
//        nameList.add(new NameDataModel("Alice"));
//        nameList.add(new NameDataModel("Bob"));
//        nameList.add(new NameDataModel("Charlie"));
//
//        //set Adapter
//        // myAdapter = new MyAdapter(this, dataNames);
//        myAdapter = new MyAdapter(this, nameList);
//        recyclerView.setAdapter(myAdapter);
    }
}