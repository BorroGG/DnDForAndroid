package com.example.dndproject.db.dao.riches;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.riches.Artworks;

import java.util.List;

@Dao
public interface ArtworksDao {
    @Query("SELECT * FROM Artworks")
    List<Artworks> getAll();

    @Insert
    long[] insertAll(Artworks... users);

    @Delete
    void delete(Artworks user);

    @Update
    void update(Artworks user);

    @Query("SELECT * FROM Artworks WHERE artworks_id = :id")
    Artworks getById(int id);
}