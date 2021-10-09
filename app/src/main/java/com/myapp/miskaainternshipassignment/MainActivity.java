package com.myapp.miskaainternshipassignment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myapp.miskaainternshipassignment.adapter.CrewAdapter;
import com.myapp.miskaainternshipassignment.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ArchitecturalFunctions {
  private CrewAdapter crewAdapter;
  private ActivityMainBinding binding;

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
    Call<List<Crew>> getSpacexCrew = RetrofitConnection.getSpacexCrewApiCalls().getAllSpacexCrew();
    getSpacexCrew.enqueue(new Callback<List<Crew>>() {
      @Override
      public void onResponse(Call<List<Crew>> call, Response<List<Crew>> crewResponse) {
        if (crewResponse != null) {
          crewAdapter.setCrewList(crewResponse.body());
        }
      }

      @Override
      public void onFailure(Call<List<Crew>> call, Throwable t) {
        Toast.makeText(MainActivity.this, "Not able to fetch data!", Toast.LENGTH_SHORT).show();
      }
    });
  }
}