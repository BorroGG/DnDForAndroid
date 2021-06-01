package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Consumables {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    public Integer consumables_id;
    public String consumables_name;
    public String consumables_type;
    public Integer consumables_price;
    public Integer consumables_weight;

    public Consumables() {
    }
}


