package com.example.dndproject.db.entities.riches;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Artworks implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer artworks_id;
    public Integer bone_number;
    public String art_name;
    public Integer art_price;

    public Artworks() {
    }
}

