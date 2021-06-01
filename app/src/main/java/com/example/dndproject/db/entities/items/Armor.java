package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Armor {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    public Integer id;
    public String extra_armor;
    public String type;
    public Integer armor_class;


    public Armor () {
    }
}


