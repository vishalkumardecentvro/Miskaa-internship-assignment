package com.myapp.miskaainternshipassignment.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.myapp.miskaainternshipassignment.ArchitecturalFunctions;
import com.myapp.miskaainternshipassignment.R;
import com.myapp.miskaainternshipassignment.RetrofitConnection;
import com.myapp.miskaainternshipassignment.adapter.CrewAdapter;
import com.myapp.miskaainternshipassignment.databinding.ActivityMainBinding;
import com.myapp.miskaainternshipassignment.room.entity.CrewEntity;
import com.myapp.miskaainternshipassignment.room.view.CrewView;
import com.wessam.library.NetworkChecker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ArchitecturalFunctions {
  private CrewAdapter crewAdapter;
  private ActivityMainBinding binding;
  private CrewView crewView;
  private boolean isConnected = true;
  private SharedPreferences sharedPreferences;

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
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.delete_all, menu);
    MenuItem menuItem = menu.findItem(R.id.deleteAll);
    if (isConnected) {

      if (menuItem != null)
        menuItem.setVisible(false);

    } else {
      if (menuItem != null)
        menuItem.setVisible(true);
    }
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.deleteAll) {
      processDeleteAllNotes();
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void instantiate() {
    checkInternetConnection();

    sharedPreferences = getSharedPreferences("crew", MODE_PRIVATE);
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
    if (isConnected) {
      getSupportActionBar().setTitle("crew members");
      loadSpacexCrew();
    } else {
      getSupportActionBar().setTitle("crew (offline)");
      loadDataFromRoom();
    }
  }

  private void loadSpacexCrew() {
    Call<List<CrewEntity>> getSpacexCrew = RetrofitConnection.getSpacexCrewApiCalls().getAllSpacexCrew();
    getSpacexCrew.enqueue(new Callback<List<CrewEntity>>() {
      @Override
      public void onResponse(Call<List<CrewEntity>> call, Response<List<CrewEntity>> crewResponse) {
        binding.tvEmpty.setVisibility(View.GONE);
        crewAdapter.setCrewList(crewResponse.body());
        saveDataOffline(crewResponse.body());
      }

      @Override
      public void onFailure(Call<List<CrewEntity>> call, Throwable t) {
        Toast.makeText(MainActivity.this, "Not able to fetch data!", Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void loadDataFromRoom() {
    crewView.getAllCrews().observe(this, crewEntityList -> {

      if (crewEntityList.size() > 0) {
        binding.tvEmpty.setVisibility(View.GONE);
        crewAdapter.setCrewList(crewEntityList);
      } else {
        binding.tvEmpty.setVisibility(View.VISIBLE);
        Toast.makeText(MainActivity.this, "No data in offline mode!", Toast.LENGTH_SHORT).show();
        crewAdapter.setCrewList(new ArrayList<>());
      }
    });
  }

  private void saveDataOffline(List<CrewEntity> crewEntityList) {
    if (!sharedPreferences.getBoolean("isCrewPopulatedInOfflineMode", false)) {
      for (CrewEntity i : crewEntityList) {
        crewView.insertCrewMembers(i);
      }
      SharedPreferences.Editor crewEditor = sharedPreferences.edit();
      crewEditor.putBoolean("isCrewPopulatedInOfflineMode", true);
      crewEditor.apply();
    }
  }

  private void processDeleteAllNotes() {
    new MaterialAlertDialogBuilder(MainActivity.this)
            .setTitle("Delete all crew members?")
            .setMessage("Once deleted cannot be undone!")
            .setNegativeButton("Cancel", (dialogInterface, i) -> {
            }).setPositiveButton("Delete", (dialogInterface, i) -> deleteAllCrewMembers()).show();
  }

  private void deleteAllCrewMembers() {
    crewView.deleteAllCrews();
    SharedPreferences.Editor crewEditor = sharedPreferences.edit();
    crewEditor.putBoolean("isCrewPopulatedInOfflineMode", false);
    crewEditor.apply();
    load();
  }

  private void checkInternetConnection() {
    isConnected = NetworkChecker.isNetworkConnected(this);
  }

}