package com.example.dndproject.db.dao.items;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.items.Armor;

import java.util.List;

@Dao
public interface ArmorDao {
    @Query("SELECT * FROM Armor")
    List<Armor> getAll();

    @Insert
    void insertAll(Armor... users);

    @Delete
    void delete(Armor user);

    @Update
    void update(Armor user);
}