package com.example.dndproject.db.dao.items;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.items.Items;
import com.example.dndproject.db.entities.items.WeaponItemsEntity;
import com.example.dndproject.db.entities.riches.TrinketsRichesEntity;

import java.util.List;

@Dao
public interface ItemsDao {
    @Query("SELECT * FROM Items")
    List<Items> getAll();

    @Insert
    void insertAll(Items... users);

    @Delete
    void delete(Items user);

    @Update
    void update(Items user);

    @Query("SELECT i.id AS items_id, w.weapon_id AS weapon_id, w.weapon_name AS weapon_name, " +
            "w.weapon_description AS weapon_description, w.weapon_type AS weapon_type, " +
            "w.weapon_price AS weapon_price, w.weapon_weight AS weapon_weight, " +
            "w.weapon_properties AS weapon_properties, w.weapon_damage AS weapon_damage FROM Items i " +
            "JOIN WeaponItemsMM mm ON i.id = mm.items_id " +
            "JOIN Weapon w ON mm.weapon_id = w.weapon_id " +
            "WHERE i.id = :items_id")
    List<WeaponItemsEntity> getWeaponItemsEntity(int items_id);
}