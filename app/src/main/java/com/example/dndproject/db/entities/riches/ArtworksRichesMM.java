package com.example.dndproject.db.entities.riches;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ArtworksRichesMM {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer id;
    @NonNull
    public Integer artworks_id;
    @NonNull
    public Integer riches_id;

    public ArtworksRichesMM() {
    }
}