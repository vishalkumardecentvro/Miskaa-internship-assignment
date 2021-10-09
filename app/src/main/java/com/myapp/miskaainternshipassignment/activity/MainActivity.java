package com.myapp.miskaainternshipassignment.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.myapp.miskaainternshipassignment.ArchitecturalFunctions;
import com.myapp.miskaainternshipassignment.RetrofitConnection;
import com.myapp.miskaainternshipassignment.adapter.CrewAdapter;
import com.myapp.miskaainternshipassignment.databinding.ActivityMainBinding;
import com.myapp.miskaainternshipassignment.room.entity.CrewEntity;
import com.myapp.miskaainternshipassignment.room.view.CrewView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ArchitecturalFunctions {
  private CrewAdapter crewAdapter;
  private ActivityMainBinding binding;
  private CrewView crewView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    instantiate();
    initialize();
    listen();
    load();
  }

  @Override
  public void instantiate() {
    crewAdapter = new CrewAdapter(this);
    crewView = ViewModelProviders.of(this).get(CrewView.class);
  }

  @Override
  public void initialize() {
    binding.rvSpacexCrew.setAdapter(crewAdapter);
  }

  @Override
  public void listen() {

  }

  @Override
  public void load() {
    loadSpacexCrew();
  }

  private void loadSpacexCrew() {
    Call<List<CrewEntity>> getSpacexCrew = RetrofitConnection.getSpacexCrewApiCalls().getAllSpacexCrew();
    getSpacexCrew.enqueue(new Callback<List<CrewEntity>>() {
      @Override
      public void onResponse(Call<List<CrewEntity>> call, Response<List<CrewEntity>> crewResponse) {
        if (crewResponse != null) {
          crewAdapter.setCrewList(crewResponse.body());
        }
        saveDataOffline(crewResponse.body());
      }

      @Override
      public void onFailure(Call<List<CrewEntity>> call, Throwable t) {
        Toast.makeText(MainActivity.this, "Not able to fetch data!", Toast.LENGTH_SHORT).show();
        Log.i("--retrofit--",t.toString());
      }
    });
  }

  private void saveDataOffline(List<CrewEntity> crewEntityList){
    for(CrewEntity i : crewEntityList){
      crewView.insertCrewMembers(i);
    }
  }
}