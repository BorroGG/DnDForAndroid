package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Weapon {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    public Integer weapon_id;
    public String weapon_name;
    public String weapon_description;
    public String weapon_type;
    public String weapon_price;
    public String weapon_weight;
    public String weapon_properties;
    public String weapon_damage;

    public Weapon() {
    }
}


