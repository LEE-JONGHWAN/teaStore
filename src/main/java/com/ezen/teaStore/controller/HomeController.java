package com.ezen.teaStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController  {

	    @RequestMapping
	    public String home(Model model) {
	        model.addAttribute("welcomeMsg", 
	        		"우리 인터넷 전통찻집 홈페이지 방문을 환영합니다.");
	        model.addAttribute("sellItems", 
	        		"판매 품목: 다양한 한국의 전통차");
	        return "teaHome";
	    }
}
