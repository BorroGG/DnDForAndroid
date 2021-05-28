package com.example.dndproject.db.dao.riches;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.riches.Precious_stonesRichesMM;

import java.util.List;

@Dao
public interface Precious_stonesRichesMMDao {
    @Query("SELECT * FROM Precious_stonesRichesMM")
    List<Precious_stonesRichesMM> getAll();

    @Insert
    void insertAll(Precious_stonesRichesMM... users);

    @Query("DELETE FROM Precious_stonesRichesMM WHERE riches_id = :riches_id")
    void delete(int riches_id);

    @Update
    void update(Precious_stonesRichesMM user);

    @Query("SELECT * FROM Precious_stonesRichesMM WHERE riches_id = :riches_id")
    Precious_stonesRichesMM getById(int riches_id);
}
