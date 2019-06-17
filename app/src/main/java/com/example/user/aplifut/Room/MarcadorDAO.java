package com.example.user.aplifut.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MarcadorDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMarcadores(List<Marcador> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMarcador(Marcador item);

    @Update
    public void updateMarcador(Marcador item);

    @Delete
    public void deleteMarcador(Marcador item);

    @Query("SELECT * FROM marcador ORDER BY id ASC")
    public List<Marcador> loadAllMarcadores();

}
