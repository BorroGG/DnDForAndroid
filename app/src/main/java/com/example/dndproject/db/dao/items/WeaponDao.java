package com.example.dndproject.db.dao.items;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.items.Weapon;

import java.util.List;

@Dao
public interface WeaponDao {
    @Query("SELECT * FROM Weapon")
    List<Weapon> getAll();

    @Insert
    void insertAll(Weapon... users);

    @Delete
    void delete(Weapon user);

    @Update
    void update(Weapon user);
}