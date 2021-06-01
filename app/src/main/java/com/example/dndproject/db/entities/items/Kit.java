package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Kit {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    public Integer kit_id;
    public String kit_name;
    public String kit_type;
    public Integer kit_price;
    public Integer kit_weight;

    public Kit() {
    }
}

