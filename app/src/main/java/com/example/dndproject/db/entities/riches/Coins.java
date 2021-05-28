package com.example.dndproject.db.entities.riches;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Coins implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer coins_id;
    public String currencie;
    public String hazard;
    public String bone_number;
    public String coins_amount;

    public Coins() {
    }
}

