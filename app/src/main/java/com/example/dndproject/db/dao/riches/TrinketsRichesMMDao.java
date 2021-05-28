package com.example.dndproject.db.dao.riches;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.riches.TrinketsRichesMM;

import java.util.List;

@Dao
public interface TrinketsRichesMMDao {
    @Query("SELECT * FROM TrinketsRichesMM")
    List<TrinketsRichesMM> getAll();

    @Insert
    void insertAll(TrinketsRichesMM... users);

    @Query("DELETE FROM TrinketsRichesMM WHERE riches_id = :riches_id")
    void delete(int riches_id);

    @Update
    void update(TrinketsRichesMM user);

    @Query("SELECT * FROM TrinketsRichesMM WHERE riches_id = :riches_id")
    TrinketsRichesMM getById(int riches_id);
}
