package com.example.dndproject.db.dao.items;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.items.Gear;

import java.util.List;

@Dao
public interface GearDao {
    @Query("SELECT * FROM Gear")
    List<Gear> getAll();

    @Insert
    void insertAll(Gear... users);

    @Delete
    void delete(Gear user);

    @Update
    void update(Gear user);
}