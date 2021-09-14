package com.ezen.teaStore.service;

import java.util.List;

import com.ezen.teaStore.domain.TradiTea;

public interface TradiTeaService {
	List<TradiTea> getAllTradiTeas();
	List<String> getTEA_COUNT();
	List<TradiTea> getTeasByName(String teaName);
	
	void todayTea(String todayTea);
}
