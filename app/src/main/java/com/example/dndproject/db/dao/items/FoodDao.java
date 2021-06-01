package com.example.dndproject.db.dao.items;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.items.Food;

import java.util.List;

@Dao
public interface FoodDao {
    @Query("SELECT * FROM Food")
    List<Food> getAll();

    @Insert
    void insertAll(Food... users);

    @Delete
    void delete(Food user);

    @Update
    void update(Food user);
}