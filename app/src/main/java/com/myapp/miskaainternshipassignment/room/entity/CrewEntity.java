package com.myapp.miskaainternshipassignment.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "crew")
public class CrewEntity {
  private String image;
  private String agency;
  private String name;
  private String wikipedia;
  private String status;

  @PrimaryKey(autoGenerate = true)
  private int crewId;

  public String getImage(){
    return image;
  }

  public String getAgency(){
    return agency;
  }

  public String getName(){
    return name;
  }

  public String getWikipedia(){
    return wikipedia;
  }

  public String getStatus(){
    return status;
  }

  public int getCrewId() {
    return crewId;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public void setAgency(String agency) {
    this.agency = agency;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setWikipedia(String wikipedia) {
    this.wikipedia = wikipedia;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setCrewId(int crewId) {
    this.crewId = crewId;
  }
}
