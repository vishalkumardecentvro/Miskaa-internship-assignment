package com.myapp.miskaainternshipassignment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetrofitConnection {
  public static SpacexCrewApiCalls spacexCrewApiCalls = null;
  private static final String baseUrl = "https://api.spacexdata.com/v4/";

  public static SpacexCrewApiCalls getSpacexCrewApiCalls() {
    if (spacexCrewApiCalls == null) {
      Retrofit retrofit = new Retrofit.Builder()
              .baseUrl(baseUrl)
              .addConverterFactory(GsonConverterFactory.create())
              .build();
      spacexCrewApiCalls = retrofit.create(SpacexCrewApiCalls.class);
    }
    return spacexCrewApiCalls;
  }

  interface SpacexCrewApiCalls {
    @GET("crew")
    void getAllSpacexCrew();

  }
}