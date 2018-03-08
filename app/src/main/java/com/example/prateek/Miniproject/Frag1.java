package com.example.prateek.Miniproject;

import android.content.Intent;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class Frag1 extends Fragment {
    TextView txt;
    Spinner sp;
    EditText un;
    String[] select;
    int sp_position;
    String selected;
    FirebaseAuth mAuth;
    public Frag1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_frag1, container, false);
        txt = (TextView)v.findViewById(R.id.forget);
        sp = (Spinner)v.findViewById(R.id.sp);
        un= (EditText)v.findViewById(R.id.username);
       // String myString = "Country";
        select = getResources().getStringArray(R.array.name);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(getContext(),android. R.layout.simple_spinner_dropdown_item,select);
       // sp_position =ad.getPosition(myString);

        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==0)
                {
                    un.setHint("First select your field");
                }
                else
                    {
                    String str = (String) sp.getItemAtPosition(i);
                    un.setHint(str+"name");
                        Toast.makeText(getContext(),str+"name", Toast.LENGTH_LONG);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getContext(),Otp.class);
                startActivity(intent);

            }
        });
        // code here
        return v;
    }


}
