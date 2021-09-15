package com.ezen.teaStore.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.teaStore.domain.TradiTea;
import com.ezen.teaStore.service.TradiTeaService;

@Controller
@RequestMapping("tea")
public class TradiTeaController {

	@Autowired
	TradiTeaService tradiTeaService;

	public TradiTeaController() {
		super();
	}
	

	@RequestMapping(value="/addTea", method = RequestMethod.GET)
	public String addTradiTea(
		@ModelAttribute("tradiTea") TradiTea tradiTea) {
		return "addTradiTea";
	}
	@RequestMapping(value="/addTea", method = RequestMethod.POST)
	public String addTradiTea(@ModelAttribute("tradiTea") 
			TradiTea tradiTea, BindingResult result) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("허용되지 않은 것 중 바인딩 시도된 항목 : " + 
			StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		tradiTeaService.addTradiTea(tradiTea);
		return "redirect:/tea/listing";
	}

	@RequestMapping("/teaDetail")
	public String getTradiTea(Model model, @RequestParam("teaId") int teaId) {

		model.addAttribute("welcomeMsg", "전통차 상품 정보");
		model.addAttribute("tradiTea", tradiTeaService.getTradiTea(teaId));

		return "traditea";
	}

	@RequestMapping("/listing/{teaName}/{price}")
	public String getTeasNamePrice(Model model, @PathVariable("teaName") String teaName,
			@MatrixVariable(pathVar = "price") Map<String, String> price) {
		model.addAttribute("welcomeMsg", "우리 인터넷 전통찻집 홈페이지 방문을 환영합니다.");
		model.addAttribute("sellitems", "판매 품목: 다양한 한국의 전통차");

		model.addAttribute("tradiTeas", tradiTeaService.getByNamePrice(teaName, price));

		return "traditeas";
	}

	@RequestMapping("/listing/{teaName}")
	public String getTeasByName(Model model, @PathVariable("teaName") String teaName) {
		model.addAttribute("welcomeMsg", "우리 인터넷 전통찻집 홈페이지 방문을 환영합니다.");
		model.addAttribute("sellitems", "판매 품목: 다양한 한국의 전통차");

		model.addAttribute("tradiTeas", tradiTeaService.getTeasByName(teaName));
		return "traditeas";
	}

	@RequestMapping("/todaytea")
	public String todayTea(Model model) {
		List<String> nameList = tradiTeaService.getTEA_COUNT();
		String todayTea = getTodaySpecial(nameList);
		tradiTeaService.todayTea(todayTea);
		return "redirect:/tea/listing";
	}

	@RequestMapping("/listing")
	public String listing(Model model) {
		model.addAttribute("welcomeMsg", "우리 인터넷 전통찻집 홈페이지 방문을 환영합니다.");
		model.addAttribute("sellitems", "판매 품목: 다양한 한국의 전통차");

		model.addAttribute("tradiTeas", tradiTeaService.getAllTradiTeas());
		return "traditeas";
	}

	static private String getTodaySpecial(List<String> nameList) {
		int idx = (int) ChronoUnit.DAYS.between(LocalDate.of(2021, 6, 22), LocalDate.now()) % nameList.size();

		return nameList.get(idx);
	}
}
