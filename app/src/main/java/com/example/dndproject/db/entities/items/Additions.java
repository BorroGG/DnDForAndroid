package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Additions {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    public Integer id;
    public String name;
    public String type;
    public Integer price;
    public Integer weight;

    public Additions() {
    }
}


