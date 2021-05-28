package com.example.dndproject.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.Source;
import com.example.dndproject.db.entities.Spell;

import java.util.List;

@Dao
public interface SpellDao {
    @Query("SELECT * FROM Spell")
    List<Spell> getAll();

    @Insert
    void insertAll(Spell... users);

    @Delete
    void delete(Spell user);

    @Update
    void update(Spell user);

    @Query("SELECT * FROM Spell WHERE id_spell = :id")
    Spell getById(int id);
}