package com.example.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.parsing.Location;
import org.springframework.stereotype.Service;

import com.example.helper.TwitterHelper;
import com.example.service.TwitterService;

import twitter4j.*;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

//twitterservice를 implement 한 구현 부분
@Service
public class TwitterServiceImpl implements TwitterService{  

	@Override
	public List<Object> getSearchList(String text , String geoType) throws Exception {

		List<Object> resultList = new ArrayList<Object>(); //최종 리턴할 데이터 형태 선언
		
		
	    TwitterHelper twitterHelper = new TwitterHelper(); //twitter4j 사용 , 작성한 쿼리로 트위터 데이터 불러옴
	    
	    GeoLocation usa = new GeoLocation(37.09024,  -95.712891); //미국 기준 위도 경도
	    GeoLocation asia = new GeoLocation(37,  127); // 대한민국 기준 위도 경도
	    GeoLocation eu = new GeoLocation(48.856614, 2.3522219  ); // 유럽 (프랑스) 기준 위도 eh

	    Query query = new Query(); //쿼리 사용을 위해 객체 선언
   	    query.setQuery(text);  // text는 클라이언트에서 받아온 검색어나 문장
   	    System.out.println("선택한 geoType : " + geoType); // 선택한 지역 value 값
   	    
	     if(geoType.equals("usa")) {  // 미국을 선택했을대 
	    	query.setGeoCode(usa, 700, Query.KILOMETERS);  // 1. 지역 좌표  , 2. 좌표 기준 반경  3.반경 단위 (킬로미터로)
	    	  
	    	List<Status> resultUsa = twitterHelper.getTweetList(query,100);
	    	
	   	    for(Status status : resultUsa) { // 불러온 트위터 데이터 리스트 for 문
	   	    	 Map<String,Object> value = new HashMap<String,Object>(); //맵 형태 만들기위해 선언
	   	    	 
	   	    	 value.put("location",status.getUser().getLocation());  // 지역 키 선언 , status에 유저에 로케이션 담음
	   	    	 value.put("lang",status.getLang()); // 언어
	   	    	 value.put("hashtag",status.getHashtagEntities()); // 해시태그
	   	    	 value.put("text",status.getText());  //타임라인
	   	    	 value.put("createAt",status.getCreatedAt());  // 등록날짜
	   	    	 value.put("favorite",status.getFavoriteCount());  // 좋아요 수
	   	    	 value.put("retwitterCount",status.getRetweetCount()); //리트윗 수
	   	    	 value.put("url","https://twitter.com/"+status.getUser().getScreenName()+"/status/"+status.getId()); // 해당 트윗 url
	   	    	 resultList.add(value);
	   	     }
	     }else if(geoType.equals("eu")) {
	    	 
	    	 	   query.setGeoCode(eu, 700, Query.KILOMETERS);
		    	   List<Status> resultEu = twitterHelper.getTweetList(query,100);
		   	    for(Status status : resultEu) {
		   	    	 Map<String,Object> value = new HashMap<String,Object>();
		   	    	 value.put("location",status.getUser().getLocation());
		   	    	 value.put("lang",status.getLang());
		   	    	 value.put("hashtag",status.getHashtagEntities());
		   	    	 value.put("text",status.getText());
		   	    	 value.put("createAt",status.getCreatedAt());
		   	    	 value.put("favorite",status.getFavoriteCount());
		   	    	 value.put("retwitterCount",status.getRetweetCount());
		   	    	 value.put("url","https://twitter.com/"+status.getUser().getScreenName()+"/status/"+status.getId());
		   	    	 resultList.add(value);
		   	     }
	     }else if(geoType.equals("asia")) {
	    	   query.setGeoCode(asia, 700, Query.KILOMETERS);
	    	   List<Status> resultAsia = twitterHelper.getTweetList(query,100);
	    	   
	   	    for(Status status : resultAsia) {
	   	    	 Map<String,Object> value = new HashMap<String,Object>();
	   	    	 value.put("location",status.getUser().getLocation());
	   	    	 value.put("lang",status.getLang());
	   	    	 value.put("hashtag",status.getHashtagEntities());
	   	    	 value.put("text",status.getText());
	   	    	 value.put("createAt",status.getCreatedAt());
	   	    	 value.put("favorite",status.getFavoriteCount());
	   	    	 value.put("retwitterCount",status.getRetweetCount());
	   	    	 value.put("url","https://twitter.com/"+status.getUser().getScreenName()+"/status/"+status.getId());
	   	    	 resultList.add(value);
	   	     }
	     }
		return resultList; // 최종 데이터 리턴
	}


	

}
