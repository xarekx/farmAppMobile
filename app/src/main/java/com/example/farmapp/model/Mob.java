package com.example.farmapp.model;

import com.google.gson.annotations.SerializedName;

public class Mob {
    @SerializedName("mob_id")
    private int id_mob;
    @SerializedName("mob_name")
    private String name_mob;
    @SerializedName("mob_zone_id")
    private int id_land_mob;

    public Mob(int id_mob, String name_mob, int id_land_mob) {
        this.id_mob = id_mob;
        this.name_mob = name_mob;
        this.id_land_mob = id_land_mob;
    }

    public int getId_mob() {
        return id_mob;
    }

    public void setId_mob(int id_mob) {
        this.id_mob = id_mob;
    }

    public String getName_mob() {
        return name_mob;
    }

    public void setName_mob(String name_mob) {
        this.name_mob = name_mob;
    }

    public int getId_land_mob() {
        return id_land_mob;
    }

    public void setId_land_mob(int id_land_mob) {
        this.id_land_mob = id_land_mob;
    }
}
