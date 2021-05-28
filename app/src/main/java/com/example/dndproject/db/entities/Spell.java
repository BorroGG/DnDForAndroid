package com.example.dndproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Spell implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer id_spell;
    public String lvl;
    public String components;
    public String source;
    public String name_spell;
    public String description_spell;
    public String school;
    public String time_spell;
    public String type_duration;
    public String type_distance;
    public String classes;

    public Spell() {
    }
}
