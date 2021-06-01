package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ArmorItemsMM {
@PrimaryKey(autoGenerate = true)
@NonNull
public Integer id;
@NonNull
public Integer armor_id;
@NonNull
public Integer items_id;

public ArmorItemsMM() {
}
}



