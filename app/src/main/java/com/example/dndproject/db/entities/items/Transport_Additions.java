package com.example.dndproject.db.entities.items;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (primaryKeys = {"id_transport", "id_addition"})
public class Transport_Additions {
    @PrimaryKey
    @NonNull
    public Integer id_transport;
    @PrimaryKey
    @NonNull
    public Integer id_addition;

    public Transport_Additions () {
    }
}



