package com.example.dndproject.db.entities.riches;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Riches implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer id_riches;
    public String title;

    public Riches() {
    }
}
