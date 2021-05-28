package com.example.dndproject.db.entities.riches;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Trinkets implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer trinkets_id;
    public Integer bone_number;
    public String trinket_name;

    public Trinkets() {
    }
}

