package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class GearItemsMM {
@PrimaryKey(autoGenerate = true)
@NonNull
public Integer id;
@NonNull
public Integer gear_id;
@NonNull
public Integer items_id;

public GearItemsMM() {
}
}

