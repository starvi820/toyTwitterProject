package com.example.twitter.instance;

import java.util.List;

import com.example.helper.TwitterHelper;

import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;



public class TwitterSearch {
	
	
	public static void main(String[] args) {
	
		  TwitterHelper twitterHelper = new TwitterHelper();

	        Query query = new Query();
	        query.setLang("ko");
	        query.setQuery("방탄소년단");
	        query.setSince("2021-03-28");

	        try {
	            List<Status> result = twitterHelper.getTweetList(query,150);

	            //조회한 객체 출력
	            twitterHelper.printOriginTweet(result);

	   
	         

	        }catch (Exception e){
	            e.printStackTrace();
	        }


	
		
		

	}
}
