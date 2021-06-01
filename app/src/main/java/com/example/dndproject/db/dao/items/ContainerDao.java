package com.example.dndproject.db.dao.items;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.items.Container;

import java.util.List;

@Dao
public interface ContainerDao {
    @Query("SELECT * FROM Container")
    List<Container> getAll();

    @Insert
    void insertAll(Container... users);

    @Delete
    void delete(Container user);

    @Update
    void update(Container user);
}