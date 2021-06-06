package com.example.dndproject.db.dao.items;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.items.WeaponItemsMM;

import java.util.List;

@Dao
public interface WeaponItemsMMDao {
    @Query("SELECT * FROM WeaponItemsMM")
    List<WeaponItemsMM> getAll();

    @Insert
    void insertAll(WeaponItemsMM... users);

    @Query("DELETE FROM WeaponItemsMM WHERE items_id = :items_id")
    void delete(int items_id);

    @Update
    void update(WeaponItemsMM user);

    @Query("SELECT * FROM WeaponItemsMM WHERE items_id = :items_id")
    WeaponItemsMM getById(int items_id);
}
