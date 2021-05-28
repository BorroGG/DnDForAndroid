package com.example.dndproject.db.entities.riches;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Precious_stones implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer precious_stones_id;
    public Integer bone_number;
    public String stone_name;
    public String stone_description;
    public Integer stone_price;

    public Precious_stones() {
    }
}


