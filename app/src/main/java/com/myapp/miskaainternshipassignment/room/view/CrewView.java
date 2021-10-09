package com.myapp.miskaainternshipassignment.room.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.myapp.miskaainternshipassignment.room.entity.CrewEntity;
import com.myapp.miskaainternshipassignment.room.repository.CrewRepository;

import java.util.List;

public class CrewView extends AndroidViewModel {
  private CrewRepository crewRepository;

  public CrewView(@NonNull Application application) {
    super(application);
    this.crewRepository = new CrewRepository(application);
  }

  public LiveData<List<CrewEntity>> getAllCrews(){
    return crewRepository.getAllCrews();
  }

  public void insertCrewMembers(CrewEntity crewEntity){
    crewRepository.insertCrewMembers(crewEntity);
  }

  public void deleteAllCrews(){
    crewRepository.deleteAllCrewMembers();
  }
}
