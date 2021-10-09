package com.myapp.miskaainternshipassignment.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.myapp.miskaainternshipassignment.room.entity.CrewEntity;

import java.util.List;

@Dao
public interface CrewDao {
  @Insert
  void insertCrewMembers(CrewEntity crewEntity);

  @Query("delete from crew")
  void deleteAllCrew();

  @Query("select * from crew")
  LiveData<List<CrewEntity>> getAllCrews();
}
