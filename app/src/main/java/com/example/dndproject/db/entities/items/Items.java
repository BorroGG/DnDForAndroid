package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Items implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer id;
    public String name;

    public Items() {
    }
}
