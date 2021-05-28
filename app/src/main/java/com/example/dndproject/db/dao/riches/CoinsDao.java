package com.example.dndproject.db.dao.riches;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.riches.Coins;

import java.util.List;

@Dao
public interface CoinsDao {
    @Query("SELECT * FROM coins")
    List<Coins> getAll();

    @Insert
    long[] insertAll(Coins... users);

    @Delete
    void delete(Coins user);

    @Update
    void update(Coins user);

    @Query("SELECT * FROM Coins WHERE coins_id = :id")
    Coins getById(int id);
}