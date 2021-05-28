package com.example.dndproject.db.dao.riches;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dndproject.db.entities.riches.ArtworksRichesEntity;
import com.example.dndproject.db.entities.riches.CoinsRichesEntity;
import com.example.dndproject.db.entities.riches.Precious_stonesRichesEntity;
import com.example.dndproject.db.entities.riches.Riches;
import com.example.dndproject.db.entities.riches.TrinketsRichesEntity;

import java.util.List;

@Dao
public interface RichesDao {
    @Query("SELECT * FROM riches")
    List<Riches> getAll();

    @Insert
    long[] insertAll(Riches... users);

    @Delete
    void delete(Riches user);

    @Update
    void update(Riches user);

    @Query("SELECT * FROM Riches WHERE id_riches = :id")
    Riches getById(int id);

    @Query("SELECT r.id_riches AS id_riches, c.coins_id AS coins_id, c.bone_number AS bone_number, " +
            "c.coins_amount AS coins_amount, c.currencie AS currencie, c.hazard AS hazard FROM Riches r " +
            "JOIN CoinsRichesMM mm ON r.id_riches = mm.riches_id " +
            "JOIN Coins c ON mm.coins_id = c.coins_id " +
            "WHERE r.id_riches = :richesId")
    List<CoinsRichesEntity> getCoinsRichesEntity(int richesId);

    @Query("SELECT r.id_riches AS id_riches, c.trinkets_id AS trinkets_id, c.bone_number AS bone_number, " +
            "c.trinket_name AS trinket_name FROM Riches r " +
            "JOIN TrinketsRichesMM mm ON r.id_riches = mm.riches_id " +
            "JOIN Trinkets c ON mm.trinkets_id = c.trinkets_id " +
            "WHERE r.id_riches = :richesId")
    List<TrinketsRichesEntity> getTrinketsRichesEntity(int richesId);

    @Query("SELECT r.id_riches AS id_riches, c.precious_stones_id AS precious_stones_id, c.bone_number AS bone_number, " +
            "c.stone_description AS stone_description, c.stone_name AS stone_name, c.stone_price AS stone_price FROM Riches r " +
            "JOIN Precious_stonesRichesMM mm ON r.id_riches = mm.riches_id " +
            "JOIN Precious_stones c ON mm.precious_stones_id = c.precious_stones_id " +
            "WHERE r.id_riches = :richesId")
    List<Precious_stonesRichesEntity> getPrecious_stonesRichesEntity(int richesId);

    @Query("SELECT r.id_riches AS id_riches, c.artworks_id AS artworks_id, c.bone_number AS bone_number, " +
            "c.art_name AS art_name, c.art_price AS art_price FROM Riches r " +
            "JOIN ArtworksRichesMM mm ON r.id_riches = mm.riches_id " +
            "JOIN Artworks c ON mm.artworks_id = c.artworks_id " +
            "WHERE r.id_riches = :richesId")
    List<ArtworksRichesEntity> getArtworksRichesEntity(int richesId);
}