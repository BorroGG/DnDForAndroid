package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Container {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    public Integer container_id;
    public String container_name;
    public String container_type;
    public Integer container_price;
    public Integer container_weight;

    public Container() {
    }
}


