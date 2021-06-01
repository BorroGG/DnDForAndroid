package com.example.dndproject.db.dao.items;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.items.Ammunition;

import java.util.List;

@Dao
public interface AmmunitionDao {
    @Query("SELECT * FROM Ammunition")
    List<Ammunition> getAll();

    @Insert
    void insertAll(Ammunition... users);

    @Delete
    void delete(Ammunition user);

    @Update
    void update(Ammunition user);
}