package com.example.service;

import java.util.List;

import twitter4j.Status;

public interface TwitterService {
	
	public List<Object> getSearchList(String text , String geoType) throws Exception;
	
	

	
}
