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
    public String weapon_type;
    public Integer weapon_price;
    public Integer weapon_weight;
    public String weapon_properties;
    public Integer weapon_damage;

    public Weapon() {
    }
}


