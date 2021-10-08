package com.myapp.miskaainternshipassignment;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ArchitecturalFunctions {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    instantiate();
    initialize();
    listen();
    load();
  }

  @Override
  public void instantiate() {

  }

  @Override
  public void initialize() {

  }

  @Override
  public void listen() {

  }

  @Override
  public void load() {
    Call<List<Crew>> getSpacexCrew = RetrofitConnection.getSpacexCrewApiCalls().getAllSpacexCrew();
    getSpacexCrew.enqueue(new Callback<List<Crew>>() {
      @Override
      public void onResponse(Call<List<Crew>> call, Response<List<Crew>> response) {
        assert response.body() != null;
        for (Crew s : response.body()) {
          Log.i("--resp--", s.getName());
        }
      }

      @Override
      public void onFailure(Call<List<Crew>> call, Throwable t) {

      }
    });
  }
}