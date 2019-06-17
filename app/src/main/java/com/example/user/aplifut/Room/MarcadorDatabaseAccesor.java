package com.example.user.aplifut.Room;

import android.arch.persistence.room.Room;
import android.content.Context;

public class MarcadorDatabaseAccesor {

    private static MarcadorDataBase PartidoDatabaseInstance;
    //Constant about the name assigned to SQLite database
    private static final String PARTIDO_DB_NAME = "marcador_db";

    private MarcadorDatabaseAccesor() {
    }

    public static MarcadorDataBase getInstance(Context context) {
        if (PartidoDatabaseInstance == null) {
// Create or open a new SQLite database, and return it as a Room Database instance.
            PartidoDatabaseInstance = Room.databaseBuilder(context,
                    MarcadorDataBase.class, PARTIDO_DB_NAME).build();
        }
        return PartidoDatabaseInstance;
    }
}
