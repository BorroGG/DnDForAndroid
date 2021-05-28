package com.example.dndproject.db.dao.riches;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.riches.Trinkets;

import java.util.List;

@Dao
public interface TrinketsDao {
    @Query("SELECT * FROM trinkets")
    List<Trinkets> getAll();

    @Insert
    long[] insertAll(Trinkets... users);

    @Delete
    void delete(Trinkets user);

    @Update
    void update(Trinkets user);

    @Query("SELECT * FROM Trinkets WHERE trinkets_id = :id")
    Trinkets getById(int id);
}