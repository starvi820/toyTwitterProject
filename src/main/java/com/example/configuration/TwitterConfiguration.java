package com.example.configuration;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConfiguration { // 사용자 키값 인증하여 인스턴스 호출 
	
	// api 사용하기 위한 키값 static (변하지 않는 불변의 고정값) 선언
	static String ConsumerKey = "gAASIHIjCHiV58PiZI4P44qPJ";
	static String ConsumerSecret = "MRlB6sXEUEo2Onqr9NQ9kj3UwyFDh1b6AldPFJUDPZQjubrNj7";
	static String AccessToken = "1430912207655641090-iQ9b4NwGcZZ1QPpuhkzTVDkBVDqIOy";
	static String AccessTokenSecret = "2ZqlzHu2olHZJXALuMxUz5e27nacpEWhLhraLiY3lMyx9";

	
	// 키값을 인증하여 트위터 데이터 불러옴
	   public Twitter getInstance(){
	       

	        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
	        configurationBuilder.setDebugEnabled(true)
	                .setOAuthConsumerKey(ConsumerKey)  // 키인증
	                .setOAuthConsumerSecret(ConsumerSecret)
	                .setOAuthAccessToken(AccessToken)  // 접근 토큰 인중
	                .setOAuthAccessTokenSecret(AccessTokenSecret);
	        TwitterFactory tf = new TwitterFactory(configurationBuilder.build()); // 위에 set 한 빌더로 트위터 객체 불러온다
	        Twitter twitter = tf.getInstance(); 
	        return twitter;
	    }
}
