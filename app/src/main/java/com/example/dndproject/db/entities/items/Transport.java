package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Transport {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    public Integer id;
    public String name;
    public Integer price;
    public Integer speed;
    public Integer weightup;
    public String type;
    public Integer addition;

    public Transport() {
    }
}

