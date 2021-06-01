package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Items {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer id;

    public Items() {
    }
}
