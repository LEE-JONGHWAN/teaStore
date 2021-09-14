package com.ezen.teaStore.repository;


import java.util.List;

import com.ezen.teaStore.domain.TradiTea;

public interface TradiTeaRepository {
	List<TradiTea> getAllTradiTeas();
	List<String> getTEA_COUNT();
	List<TradiTea> getTeasByName(String teaName);

	void todayTea(String todayTea);
}


