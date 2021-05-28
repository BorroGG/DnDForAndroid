package com.example.dndproject.db.dao.riches;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.riches.ArtworksRichesMM;

import java.util.List;

@Dao
public interface ArtworksRichesMMDao {
    @Query("SELECT * FROM ArtworksRichesMM")
    List<ArtworksRichesMM> getAll();

    @Insert
    void insertAll(ArtworksRichesMM... users);

    @Query("DELETE FROM ArtworksRichesMM WHERE riches_id = :riches_id")
    void delete(int riches_id);

    @Update
    void update(ArtworksRichesMM user);

    @Query("SELECT * FROM ArtworksRichesMM WHERE riches_id = :riches_id")
    ArtworksRichesMM getById(int riches_id);
}
