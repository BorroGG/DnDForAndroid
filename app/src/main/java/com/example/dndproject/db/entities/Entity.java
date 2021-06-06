package com.example.dndproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@androidx.room.Entity
public class Entity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer id;
    public String name;
    public String hp;
    public String description;
    public Integer speed_id;
    public Integer stats_id;
    public Integer armor_id;
    public Integer skills_id;
    public Integer fillings_id;
    public Integer actions_id;
    public Integer languages_id;
    public Integer abilities_id;
    public Integer resistances_id;
    public String hits;
    public String danger;
    public String exp;
    public Integer items_id;
    public Integer riches_id;

    public Entity() {
    }
}
