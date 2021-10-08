package com.myapp.miskaainternshipassignment;

import java.util.List;

public class Crew {
	private String image;
	private String agency;
	private String name;
	private String wikipedia;
	private String id;
	private List<String> launches;
	private String status;

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

	public String getId(){
		return id;
	}

	public List<String> getLaunches(){
		return launches;
	}

	public String getStatus(){
		return status;
	}
}