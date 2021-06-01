package com.example.dndproject.db.dao.riches;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.riches.Precious_stones;

import java.util.List;

@Dao
public interface Precious_stonesDao {
    @Query("SELECT * FROM precious_stones")
    List<Precious_stones> getAll();

    @Insert
    long[] insertAll(Precious_stones... users);

    @Delete
    void delete(Precious_stones user);

    @Update
    void update(Precious_stones user);

    @Query("SELECT * FROM Precious_stones WHERE precious_stones_id = :id")
    Precious_stones getById(int id);
}