package com.myapp.miskaainternshipassignment.room.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.myapp.miskaainternshipassignment.room.Database;
import com.myapp.miskaainternshipassignment.room.dao.CrewDao;
import com.myapp.miskaainternshipassignment.room.entity.CrewEntity;

import java.util.List;

public class CrewRepository {
  private CrewDao crewDao;

  public CrewRepository(Application application) {
    Database database = Database.getDatabaseInstance(application);
    this.crewDao = database.crewDao();
  }

  public LiveData<List<CrewEntity>> getAllCrews() {
    return crewDao.getAllCrews();
  }

  public void insertCrewMembers(CrewEntity crewEntity) {
    new InsertCrewMembers(crewDao).execute(crewEntity);
  }

  public void deleteAllCrewMembers() {
    new DeleteAllNotes(crewDao).execute();
  }

  private static class InsertCrewMembers extends AsyncTask<CrewEntity, Void, Void> {
    private CrewDao crewDao;

    public InsertCrewMembers(CrewDao crewDao) {
      this.crewDao = crewDao;
    }

    @Override
    protected Void doInBackground(CrewEntity... crewEntities) {
      crewDao.insertCrewMembers(crewEntities[0]);
      return null;
    }
  }

  private static class DeleteAllNotes extends AsyncTask<Void, Void, Void> {
    private CrewDao crewDao;

    public DeleteAllNotes(CrewDao crewDao) {
      this.crewDao = crewDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      crewDao.deleteAllCrew();
      return null;
    }
  }
}
