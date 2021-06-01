package com.example.dndproject.db.dao.items;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.items.Tool;

import java.util.List;

@Dao
public interface ToolDao {
    @Query("SELECT * FROM Tool")
    List<Tool> getAll();

    @Insert
    void insertAll(Tool... users);

    @Delete
    void delete(Tool user);

    @Update
    void update(Tool user);
}