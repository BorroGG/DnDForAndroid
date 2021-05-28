package com.example.dndproject.db.dao.riches;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.riches.CoinsRichesMM;

import java.util.List;

@Dao
public interface CoinsRichesMMDao {
    @Query("SELECT * FROM CoinsRichesMM")
    List<CoinsRichesMM> getAll();

    @Insert
    void insertAll(CoinsRichesMM... users);

    @Query("DELETE FROM CoinsRichesMM WHERE riches_id = :riches_id")
    void delete(int riches_id);

    @Update
    void update(CoinsRichesMM user);

    @Query("SELECT * FROM CoinsRichesMM WHERE riches_id = :riches_id")
    CoinsRichesMM getByRichesId(int riches_id);
}
