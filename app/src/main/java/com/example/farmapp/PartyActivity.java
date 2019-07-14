package com.example.farmapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmapp.model.Party;

import java.util.ArrayList;
import java.util.List;

public class PartyActivity extends AppCompatActivity {

    List<String> party = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);

        String party_name = getIntent().getStringExtra("party_name");
        System.out.println(party_name);

        final EditText mParty_Et = findViewById(R.id.party_et);
        final Button mPartySaveBtn = findViewById(R.id.save_party_btn);
        final Button mCreatePartyBtn = findViewById(R.id.create_party_button);

        mPartySaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final LinearLayout linearLayout = findViewById(R.id.party_member_layout);
                linearLayout.removeAllViews();
                party.add(mParty_Et.getText().toString());

                if(party.size()>1) {
                    mCreatePartyBtn.setVisibility(View.VISIBLE);
                    mCreatePartyBtn.setEnabled(true);
                }
                if(party.size()<8) {

                for (int i=0; i<party.size();i++) {
                    final TextView textView = new TextView(getApplicationContext());
                    textView.setText(party.get(i));
                    textView.setGravity(Gravity.CENTER);

                    textView.setPadding(4,4,4,4);
                    textView.setWidth(60);
                    textView.setHeight(90);
                    textView.setBackgroundColor(Color.rgb(0, 231, 192));
                    textView.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
                    linearLayout.addView(textView);

                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(PartyActivity.this, textView.getText().toString()+"", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                mParty_Et.setText("");
            } else {

                    mPartySaveBtn.setEnabled(false);

                    for (int i=0; i<party.size();i++) {
                        final TextView textView = new TextView(getApplicationContext());
                        textView.setText(party.get(i));
                        textView.setGravity(Gravity.CENTER);
                        textView.setPadding(4,4,4,4);
                        textView.setWidth(60);
                        textView.setHeight(90);
                        textView.setBackgroundColor(Color.rgb(0, 231, 192));
                        textView.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
                        linearLayout.addView(textView);

                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(PartyActivity.this, textView.getText().toString()+"", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }


                    Toast.makeText(PartyActivity.this, "Party count max 8 people", Toast.LENGTH_SHORT).show();
                }
            }

        });


        Toast.makeText(this, "Select your friends to farm !", Toast.LENGTH_SHORT).show();

       mCreatePartyBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mPartySaveBtn.setEnabled(false);
               Toast.makeText(PartyActivity.this, "Party Created", Toast.LENGTH_SHORT).show();

           }
       });




    }
//

    public List<String> createParty(String partyMember) {

        List<String> party = new ArrayList<>();

        party.add(partyMember);

        return party;
    }
}
