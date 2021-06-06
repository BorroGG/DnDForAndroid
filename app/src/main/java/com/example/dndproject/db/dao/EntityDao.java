package com.example.dndproject.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.dndproject.db.entities.Entity;

import java.util.List;

@Dao
public interface EntityDao {
    @Query("SELECT * FROM Entity")
    List<Entity> getAll();

    @Insert
    void insertAll(Entity... users);

    @Delete
    void delete(Entity user);

    @Update
    void update(Entity user);
}