package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Gear {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    public Integer gear_id;
    public String gear_name;
    public String gear_type;
    public Integer gear_price;
    public Integer gear_weight;

    public Gear() {
    }
}


