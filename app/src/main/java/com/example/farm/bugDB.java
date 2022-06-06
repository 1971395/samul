package com.example.farm;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {bug.class}, version = 1)
public abstract class bugDB extends RoomDatabase {
    public abstract bugDao bugDao();
}
