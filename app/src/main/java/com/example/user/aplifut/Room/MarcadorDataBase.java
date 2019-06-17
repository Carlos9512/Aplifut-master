package com.example.user.aplifut.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Marcador.class}, version = 1)
public abstract class MarcadorDataBase extends RoomDatabase {
    public abstract MarcadorDAO marcadorDAO();
}
