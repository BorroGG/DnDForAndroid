package com.example.dndproject.db.dao.items;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.items.Armor1;

import java.util.List;


@Dao
public interface Armor1Dao {
    @Query("SELECT * FROM Armor1")
    List<Armor1> getAll();

    @Insert
    void insertAll(Armor1... users);

    @Delete
    void delete(Armor1 user);

    @Update
    void update(Armor1 user);
}