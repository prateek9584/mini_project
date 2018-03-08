package com.example.prateek.Miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Frag2 extends Fragment {
   // TextView txt;
    Spinner sp;
    EditText un,eMail,password,confPassword;
    FirebaseAuth mAuth;
    Button Register;
    String[] select;
    int sp_position=0;
    String selected;
    DatabaseReference userdatabase;

    public Frag2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag2, container, false);
        //txt = (TextView) v.findViewById(R.id.forget);
        sp = (Spinner) v.findViewById(R.id.spinner);
        un = (EditText) v.findViewById(R.id.user);
        eMail = (EditText) v.findViewById(R.id.email);
        password = (EditText) v.findViewById(R.id.password);
        confPassword = (EditText) v.findViewById(R.id.confirmpassword);
        Register = (Button) v.findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
        userdatabase = FirebaseDatabase.getInstance().getReference("user_database");

       Register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             AddUser();
           }
       });
        // String myString = "Country";
        select = getResources().getStringArray(R.array.name);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, select);
        // sp_position =ad.getPosition(myString);

        sp.setAdapter(ad);


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    un.setHint("First select your field");
                } else {
                    String str = (String) sp.getItemAtPosition(i);
                    sp_position = i;
                    selected = str;
                    un.setHint(str + "name");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        // code here
        return v;

    }
    public void AddUser()
    {
        String Sname,Spassword,SconfirmPassword,SeMail,SuserType;
        Sname=un.getText().toString().trim();
        Spassword = password.getText().toString().trim();
        SconfirmPassword = confPassword.getText().toString().trim();
        SeMail = eMail.getText().toString().trim();
        SuserType = selected;
        if(!TextUtils.isEmpty(Sname))
        {
            Toast.makeText(getActivity(),"enter the name", Toast.LENGTH_LONG).show();
            return;
        }
        if(!TextUtils.isEmpty(SeMail))
        {
            Toast.makeText(getActivity(),"enter the email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(SuserType))
        {
            Toast.makeText(getActivity(),"select the user type",Toast.LENGTH_LONG).show();
            return;
        }
        if(!TextUtils.isEmpty(Spassword) && !TextUtils.isEmpty(SconfirmPassword)){
            if (TextUtils.equals(SconfirmPassword,Spassword))
            {
                //AddUser user = new AddUser(Sname,Spassword,SuserType,SeMail);
                //String id = userdatabase.push().getKey();
                //userdatabase.child(id).setValue(user);
                Toast.makeText(getActivity(),"user id added",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getActivity(),"passwod didnt match", Toast.LENGTH_LONG).show();
                return;
            }

        }
        else {
            Toast.makeText(getActivity(),"Enter password", Toast.LENGTH_LONG).show();
            return;
        }
    }
}// code here






