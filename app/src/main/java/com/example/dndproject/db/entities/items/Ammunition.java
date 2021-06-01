package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Ammunition {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    public Integer ammunition_id;
    public String ammunition_name;
    public String ammunition_type;
    public Integer ammunition_price;
    public Integer ammunition_weight;

    public Ammunition () {
    }
}

