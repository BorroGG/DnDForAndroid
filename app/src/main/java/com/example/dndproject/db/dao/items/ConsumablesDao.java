package com.example.dndproject.db.dao.items;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.items.Consumables;

import java.util.List;

@Dao
public interface ConsumablesDao {
    @Query("SELECT * FROM Consumables")
    List<Consumables> getAll();

    @Insert
    void insertAll(Consumables... users);

    @Delete
    void delete(Consumables user);

    @Update
    void update(Consumables user);
}