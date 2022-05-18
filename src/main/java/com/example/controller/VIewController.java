package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // restContoller 가 아니면 라우터 느낌
public class VIewController {
	
	@RequestMapping(value="/")
	public String index() {
		return "index";
	}

}
