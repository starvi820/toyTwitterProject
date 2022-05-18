package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.TwitterService;

import twitter4j.Status;

@ComponentScan
@RestController
@RequestMapping(value="/api/twitter")
public class TwitterController {
	
	@Autowired
	private TwitterService twitterService;
	
	@GetMapping("/searchList")   //text , geoType 을 받아와서 받은 서비스 리턴 -> get 방식 
	public List<Object> getSearchTwitter(@RequestParam(value="text",required=false)String text,
			@RequestParam(value="geoType",required=false)String geoType) throws Exception	{
		List<Object> data = twitterService.getSearchList(text,geoType);
		return data;
	}
	
}
