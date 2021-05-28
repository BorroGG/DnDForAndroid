package com.example.dndproject.db.entities.riches;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CoinsRichesMM {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer id;
    @NonNull
    public Integer coins_id;
    @NonNull
    public Integer riches_id;

    public CoinsRichesMM() {
    }
}
