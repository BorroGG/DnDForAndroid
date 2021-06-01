package com.example.dndproject.db.dao.items;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.items.Transport;

import java.util.List;

@Dao
public interface TransportDao {
    @Query("SELECT * FROM Transport")
    List<Transport> getAll();

    @Insert
    void insertAll(Transport... users);

    @Delete
    void delete(Transport user);

    @Update
    void update(Transport user);
}