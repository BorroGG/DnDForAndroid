package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tool {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    public Integer tool_id;
    public String tool_name;
    public String tool_type;
    public Integer tool_price;
    public Integer tool_weight;

    public Tool() {
    }
}

