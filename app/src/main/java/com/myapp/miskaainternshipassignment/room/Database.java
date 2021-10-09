package com.myapp.miskaainternshipassignment.room;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.myapp.miskaainternshipassignment.room.dao.CrewDao;
import com.myapp.miskaainternshipassignment.room.entity.CrewEntity;

@androidx.room.Database(entities = {CrewEntity.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
  public static Database databaseInstance;

  public static synchronized Database getDatabaseInstance(Context context) {
    if (databaseInstance == null) {
      databaseInstance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "crew")
              .fallbackToDestructiveMigrationFrom()
              .build();
    }
    return databaseInstance;
  }

  public abstract CrewDao crewDao();
}
