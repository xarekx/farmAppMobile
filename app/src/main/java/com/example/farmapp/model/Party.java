package com.example.farmapp.model;

import java.util.ArrayList;
import java.util.Date;

public class Party {
    private int party_id;
    private String party_name;
    private String party_date;


    public Party(int party_id, String party_name, String party_date) {
        this.party_id = party_id;
        this.party_name = party_name;
        this.party_date = party_date;
    }

    public Party() {
    }


    public int getParty_id() {
        return party_id;
    }

    public void setParty_id(int party_id) {
        this.party_id = party_id;
    }

    public String getParty_name() {
        return party_name;
    }

    public void setParty_name(String party_name) {
        this.party_name = party_name;
    }

    public String getParty_date() {
        return party_date;
    }

    public void setParty_date(String party_date) {
        this.party_date = party_date;
    }







}
