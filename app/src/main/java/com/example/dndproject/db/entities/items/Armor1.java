package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Armor1 {
    public Integer armor_id;
    public String armor_name;
    public String armor_type;
    public Integer armor_price;
    public Integer armor_weight;
    public String armor_class;

    public Armor1 () {
    }
}

