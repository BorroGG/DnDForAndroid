package com.example.dndproject.db.dao.items;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.items.Transport_Additions;

import java.util.List;

@Dao
public interface Transport_AdditionsDao {
    @Query("SELECT * FROM Transport_additions")
    List<Transport_Additions> getAll();

    @Insert
    void insertAll(Transport_Additions... users);

    @Delete
    void delete(Transport_Additions user);

    @Update
    void update(Transport_Additions user);
}