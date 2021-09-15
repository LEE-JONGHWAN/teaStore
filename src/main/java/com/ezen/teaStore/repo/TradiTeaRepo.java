package com.ezen.teaStore.repo;

import java.util.List;
import java.util.Map;

import com.ezen.teaStore.domain.TradiTea;

public interface TradiTeaRepo {
	List<TradiTea> getAllTradiTeas();
	List<String> getTEA_COUNT();
	void todayTea(String todayTea);
	List<TradiTea> getTeasByName(String teaName);
	List<TradiTea> getByNamePrice(String teaName, 
			Map<String, String> price);
	TradiTea getTradiTea(int teaId);
	void addTradiTea(TradiTea tradiTea);
}
