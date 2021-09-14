package com.ezen.teaStore.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.teaStore.service.TradiTeaService;

@Controller
@RequestMapping("tea") 
public class TradiTeaController {
	
	@Autowired
	TradiTeaService tradiTeaService;
	
	public TradiTeaController() {
		super();
	}

	
	@RequestMapping("/listing/{teaName}") 
	public String getTeasByName(Model model,
			@PathVariable("teaName") String teaName) {

		model.addAttribute("welcomeMsg", 
				"우리 인터넷 전통찻집 홈페이지 방문을 환영합니다.");
		model.addAttribute("sellItems", 
				"판매 품목: 다양한 한국의 전통차");
		
		model.addAttribute("tradiTeas", 
				tradiTeaService.getTeasByName(teaName));
		return "traditeas";
		
	}
	
	@RequestMapping("/todaytea") 
	public String todayTea(Model model) {
	List<String> teaCount = tradiTeaService.getTEA_COUNT();
	String todayTea = getTodaySpecial(teaCount);
//	tradiTeaService.todayTea(todayTea);
	tradiTeaService.todayTea("율무차");
	return "redirect:/tea/listing";
	}
	

	@RequestMapping("/listing") 
	public String listing(Model model) {

		model.addAttribute("welcomeMsg", 
				"우리 인터넷 전통찻집 홈페이지 방문을 환영합니다.");
		model.addAttribute("sellItems", 
				"판매 품목: 다양한 한국의 전통차");
		
		model.addAttribute("tradiTeas", 
				tradiTeaService.getAllTradiTeas());
		return "traditeas";
	}

	
	static private String getTodaySpecial(List<String> nameList) {
		int idx = (int)ChronoUnit.DAYS.between(
				LocalDate.of(2021, 6, 22), LocalDate.now()) 
				% nameList.size();
		
		return nameList.get(idx);
	}

	
}
