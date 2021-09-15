package com.ezen.teaStore.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.teaStore.domain.TeaType;
import com.ezen.teaStore.domain.TradiTea;
import com.ezen.teaStore.repo.TradiTeaRepo;
import com.ezen.teaStore.service.TradiTeaService;

@Service
public class TradiTeaServiceImpl implements TradiTeaService {
	@Autowired
	TradiTeaRepo tradiTeaRepo;
	
	@Override
	public List<TradiTea> getAllTradiTeas() {
		return tradiTeaRepo.getAllTradiTeas();
	}

	@Override
	public List<String> getTEA_COUNT() {
		return tradiTeaRepo.getTEA_COUNT();
	}

	@Override
	public void todayTea(String todayTea) {
		tradiTeaRepo.todayTea(todayTea);
	}

	@Override
	public List<TradiTea> getTeasByName(String teaName) {
		return tradiTeaRepo.getTeasByName(teaName);
	}

	@Override
	public List<TradiTea> getByNamePrice(String teaName, 
			Map<String, String> price) {
		return tradiTeaRepo.getByNamePrice(teaName, price);
	}

	@Override
	public TradiTea getTradiTea(int teaId) {
		return tradiTeaRepo.getTradiTea(teaId);
	}

	@Override
	public void addTradiTea(TradiTea tradiTea) {
		tradiTeaRepo.addTradiTea(tradiTea);;
	}
}
