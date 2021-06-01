package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Food {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    public Integer food_id;
    public String food_name;
    public String food_type;
    public Integer food_price;

    public Food() {
    }
}

