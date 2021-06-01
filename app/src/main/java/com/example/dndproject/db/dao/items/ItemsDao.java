package com.example.dndproject.db.dao.items;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.items.Items;

import java.util.List;

@Dao
public interface ItemsDao {
    @Query("SELECT * FROM Items")
    List<Items> getAll();

    @Insert
    void insertAll(Items... users);

    @Delete
    void delete(Items user);

    @Update
    void update(Items user);
}