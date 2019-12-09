package com.example.farmapp.model;

public class PartyMember {

    private String name;
    private int u_id;

    public PartyMember(String name, int u_id) {
        this.u_id = u_id;
        this.name = name;
    }

    public PartyMember() {

    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }


}
