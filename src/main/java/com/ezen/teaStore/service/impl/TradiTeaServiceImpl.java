package com.ezen.teaStore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.teaStore.domain.TradiTea;
import com.ezen.teaStore.repository.TradiTeaRepository;
import com.ezen.teaStore.service.TradiTeaService;

@Service
public class TradiTeaServiceImpl implements TradiTeaService {
	@Autowired
	TradiTeaRepository tradiTeaRepo;
	
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
}
