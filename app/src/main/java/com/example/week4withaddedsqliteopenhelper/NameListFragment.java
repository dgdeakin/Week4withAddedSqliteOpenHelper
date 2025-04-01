package com.example.week4withaddedsqliteopenhelper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link NameListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class NameListFragment extends Fragment {

    RecyclerView recyclerView;

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
    MyAdapter adapter;
    private List<NameDataModel> nameList;
    private DatabaseHelper dbHelper;
    private EditText nameInput;
    private Button addButton, updateButton, deleteButton;
    private NameDataModel selectedName;


//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public NameListFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment NameListFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static NameListFragment newInstance(String param1, String param2) {
//        NameListFragment fragment = new NameListFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_name_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        nameInput = view.findViewById(R.id.nameInput);
        addButton = view.findViewById(R.id.addButton);
        updateButton = view.findViewById(R.id.updateButton);
        deleteButton = view.findViewById(R.id.deleteButton);

        nameList = new ArrayList<>();
//        adapter = new MyAdapter(nameList, this); // Pass fragment reference
        adapter = new MyAdapter(getContext(), nameList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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

        //set Adapter
        // myAdapter = new MyAdapter(this, dataNames);
//        adapter = new MyAdapter(getContext(), nameList);
        recyclerView.setAdapter(adapter);

        dbHelper = new DatabaseHelper(getContext());
        loadNames();

        addButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            Toast.makeText(getContext(), name, Toast.LENGTH_LONG).show();
            if(!name.isEmpty()){
                if(selectedName == null){
                    dbHelper.addName(name);
                    nameInput.setText("");
                    loadNames();
                }
                else {
                    selectedName = null;
                    nameInput.setText("");
                    updateButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                    addButton.setText("Add");
                }
            }
        });

        updateButton.setOnClickListener(v -> {
            if (selectedName != null) {
                String newName = nameInput.getText().toString();
                if (!newName.isEmpty()) {
                    dbHelper.updateName(selectedName.getId(), newName);
                    nameInput.setText("");
                    selectedName = null;
                    updateButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                    addButton.setText("Add");
                    loadNames();
                }
            }
        });

        deleteButton.setOnClickListener(v -> {
            if (selectedName != null) {
                dbHelper.deleteName(selectedName.getId());
                nameInput.setText("");
                selectedName = null;
                updateButton.setEnabled(false);
                deleteButton.setEnabled(false);
                addButton.setText("Add");
                loadNames();
            }
        });


        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
        return view;

//        return inflater.inflate(R.layout.fragment_name_list, container, false);
    }

    private void loadNames(){
        nameList = dbHelper.getAllNames();
        adapter.updateList(nameList);
        Toast.makeText(getContext(), nameList.get(0).getNameText(), Toast.LENGTH_LONG).show();
        Log.i("NameList", nameList.toString());
    }

    public void onItemClicked(NameDataModel name){
        selectedName = name;
        nameInput.setText(name.getNameText());
        updateButton.setEnabled(true);
        deleteButton.setEnabled(true);
        addButton.setText("Cancel");
    }


}