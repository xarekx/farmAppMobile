package com.example.farmapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmapp.Retrofit.RetrofitClientInstance;
import com.example.farmapp.Retrofit.RetrofitPartyData;
import com.example.farmapp.model.Party;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("NullableProblems")
public class PartyActivity extends AppCompatActivity {

    final String TAG = "PartyActivity";
    ArrayList<String> party = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);

        final String party_name = getIntent().getStringExtra("party_name");

        final EditText mParty_Et = findViewById(R.id.party_et);
        final Button mPartySaveBtn = findViewById(R.id.save_party_btn);
        final Button mCreatePartyBtn = findViewById(R.id.create_party_button);
        final TextView mPartyName_Et = findViewById(R.id.party_name_tv);
        mPartyName_Et.setText(party_name);


        mPartySaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final LinearLayout linearLayout = findViewById(R.id.party_member_layout);
                linearLayout.removeAllViews();
                party.add(mParty_Et.getText().toString());

                // visibility of create Party button
                if(party.size()>1) {
                    mCreatePartyBtn.setVisibility(View.VISIBLE);
                    mCreatePartyBtn.setEnabled(true);
                }

                if(party.size()<8) {

                for (int party_guest=0; party_guest<party.size();party_guest++) {
                    final TextView textView = new TextView(getApplicationContext());
                    textView.setText(party.get(party_guest));
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
                            System.out.println(textView.getText().toString());
                            for(int z=0; z<party.size();z++) {
                                if(textView.getText().toString().equals(party.get(z))) {
                                    party.remove(z);
                                    linearLayout.removeView(v);
                                    //if less than 2 member in party
                                    if(party.size()<2) {
                                        mCreatePartyBtn.setVisibility(View.INVISIBLE);
                                        mCreatePartyBtn.setEnabled(false);
                                    }
                                }
                            }
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
                                for(int z=0; z<party.size();z++) {
                                    if(textView.getText().toString().equals(party.get(z))) {
                                        party.remove(z);
                                        linearLayout.removeView(v);
                                        if(party.size()<8) {
                                            mPartySaveBtn.setEnabled(true);
                                        }
                                        if(party.size()<2) {
                                            mCreatePartyBtn.setVisibility(View.INVISIBLE);
                                            mCreatePartyBtn.setEnabled(false);
                                        }
                                    }
                                }
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
               Intent intent = new Intent(getApplicationContext(),PathActivity.class);
               startActivity(intent);
               Thread thread = new Thread(new Runnable() {
                   @Override
                   public void run() {
                       Date date = new Date();
                       String firstDate = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).format(date);
                        createParty(new Party(party_name,firstDate,ArrayToString(party)));

                   }
               });
               thread.start();
               Toast.makeText(PartyActivity.this, "Party Created", Toast.LENGTH_SHORT).show();
           }
       });

    }

    public void createParty(Party party) {
        RetrofitPartyData retrofitPartyData = RetrofitClientInstance.getRetrofitInstance().create(RetrofitPartyData.class);
        Call<Party> createPt = retrofitPartyData.createParty(party);

        createPt.enqueue(new Callback<Party>() {
            @Override
            public void onResponse(Call<Party> call, Response<Party> response) {
                Log.d(TAG,"SUCCESS");
            }

            @Override
            public void onFailure(Call<Party> call, Throwable t) {
               Log.d(TAG,"FAILURE",t);

            }
        });
    }

    public String ArrayToString(ArrayList arrayList) {
        return "{\"names\":\""+arrayList.toString()+"\"}";
    }
}
