package com.example.dndproject.db.dao.items;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.items.Kit;

import java.util.List;

@Dao
public interface KitDao {
    @Query("SELECT * FROM Kit")
    List<Kit> getAll();

    @Insert
    void insertAll(Kit... users);

    @Delete
    void delete(Kit user);

    @Update
    void update(Kit user);
}