package com.example.chaapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {
    EditText username, password,FirstName,LastName,Phone,Email,Address;
    Button registerButton;
    String user, pass,fname1,lname1,Phno,email,address;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        FirstName = (EditText)findViewById(R.id.fname);
        LastName = (EditText)findViewById(R.id.lname);
        Phone= (EditText)findViewById(R.id.phn);
        Email = (EditText)findViewById(R.id.email);
        Address= (EditText)findViewById(R.id.add);
        registerButton = (Button)findViewById(R.id.registerButton);
        login = (TextView)findViewById(R.id.login);


        Firebase.setAndroidContext(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();
                fname1 =FirstName.getText().toString();
                lname1=LastName.getText().toString();
                Phno=Phone.getText().toString();
                email= Email.getText().toString();
                address =Address.getText().toString();


                if(user.equals("")){
                    username.setError("can't be blank");
                }
                else if(pass.equals("")){
                    password.setError("can't be blank");
                }
                else if(fname1.equals("")){
                    FirstName.setError("can't be blank");
                }
                else if(lname1.equals("")){
                    LastName.setError("can't be blank");
                }
                else if(Phno.equals("")){
                    Phone.setError("can't be blank");
                }
                else if(email.equals("")){
                    Email.setError("can't be blank");
                }
                else if(address.equals("")){
                    Address.setError("can't be blank");
                }
                else if(!user.matches("[A-Za-z0-9]+")){
                    username.setError("only alphabet or number allowed");
                }
                else if(user.length()<5){
                    username.setError("at least 5 characters long");
                }
                else if(pass.length()<5){
                    password.setError("at least 5 characters long");
                }
                else if(Phno.length()<10){
                    Phone.setError("at least 10 characters long");
                }
                else {
                    final ProgressDialog pd = new ProgressDialog(Register.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    final String url = "https://chatapplication-4c5b8.firebaseio.com/users/users.json";

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String s) {
                            Firebase reference = new Firebase("https://chatapplication-4c5b8.firebaseio.com/users");

                            if(s.equals("null")) {
                                reference.child(user).child("password").setValue(pass);
                                reference.child(user).child("FirstName").setValue(fname1);
                                reference.child(user).child("Lastname").setValue(lname1);
                                reference.child(user).child("Phone").setValue(Phno);
                                reference.child(user).child("Email").setValue(email);
                                reference.child(user).child("Address").setValue(address);

                                Intent i=new Intent(Register.this, Login.class);
                                startActivity(i);

                                Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                            }
                            else {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(user)) {
                                        reference.child(user).child("password").setValue(pass);


                                        Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(Register.this, "username already exists", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            pd.dismiss();
                        }

                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError );
                            pd.dismiss();
                        }
                    });

                    RequestQueue rQueue = Volley.newRequestQueue(Register.this);
                    rQueue.add(request);
                }
            }
        });
    }
}
