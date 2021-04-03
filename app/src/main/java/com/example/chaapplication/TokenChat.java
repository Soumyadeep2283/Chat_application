package com.example.chaapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.chaapplication.UserDetails.Token;

public class TokenChat extends AppCompatActivity {
    TextView Tokentext,Dpt;
    Button TokenButton,prvButton;
    FirebaseDatabase mdatabase;
    DatabaseReference reference3 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_chat);
        Tokentext=findViewById(R.id.tokenNo);
        Dpt=findViewById(R.id.Dptname);
        TokenButton=findViewById(R.id.Next);
        prvButton=findViewById(R.id.Prev);

        //reference3 = new Firebase("https://chatapplication-4c5b8.firebaseio.com/Token/"+ UserDetails.username + "_" + Token);
        //reference3 = new DatabaseReference("https://chatapplication-4c5b8.firebaseio.com/Token/");

        //mdatabase = FirebaseDatabase.getInstance();
        //reference3=mdatabase.getReference(Token);
        //reference3.setValue("https://chatapplication-4c5b8.firebaseio.com/Token/");


        TokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(Token=="") {
                 Random r = new Random();
                 int randomvalue = r.nextInt(100);


                  Token = String.valueOf(randomvalue);
                 Toast.makeText(TokenChat.this, "Random number is" + Token, Toast.LENGTH_LONG).show();
                 Tokentext.setText(Token);
             }

            }
        });
    }
}
