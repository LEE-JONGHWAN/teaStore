package com.ezen.teaStore.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ezen.teaStore.domain.TeaType;
import com.ezen.teaStore.domain.TradiTea;
import com.ezen.teaStore.repository.TradiTeaRepository;

//@formatter:off
@Repository
public class MariaTradiTeaRepository implements TradiTeaRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<TradiTea> getAllTradiTeas() {
		var params = new HashMap<String, Object>();
		String query = 	"SELECT 상품ID, 차이름, 제고수량, 제조일,"
				+ " 용량, 가격, 설명 FROM 전통차";
		List<TradiTea> result = jdbcTemplate.query(query, 
				params, new TradiTeaMapper());
		return result;
	}
	
	private static final class TradiTeaMapper 
			implements RowMapper<TradiTea> {
		@Override
		public TradiTea mapRow(ResultSet rs, int rowNum) 
				throws SQLException {
			TradiTea tradiTea = new TradiTea();
			int idx = 1;
			
			tradiTea.setTeaID(rs.getInt(idx++));
			tradiTea.setTeaName(rs.getString(idx++));
			tradiTea.setStock(rs.getInt(idx++));
			tradiTea.setProdDate(rs.getDate(idx++));
			tradiTea.setAmount(rs.getString(idx++));
			tradiTea.setPrice(rs.getDouble(idx++));
			tradiTea.setProdDesc(rs.getString(idx++));
			return tradiTea;
		}
	}

	@Override
	public List<String> getTEA_COUNT() {
		var params = new HashMap<String, Object>();
		String query = "SELECT 차이름 FROM 전통차";
		List<String> nameList = jdbcTemplate.queryForList(
				query, params, String.class);
		return nameList;
	}	
	
	@Override
	public void todayTea(String todayTea) {
		String sql = "update 전통차 set 가격_원 = 가격, 가격=가격*0.9 " 
				+ "where 차이름 = :todayTea and 가격_원 is null";

		
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("todayTea", todayTea);

		jdbcTemplate.update(sql, params);
		
		sql =  "update 전통차 set 가격=가격_원, 가격_원 = null " 
			 + "where 차이름 != :todayTea and 가격 != 가격_원 "
			 + "and 가격_원 is not null";
		
		jdbcTemplate.update(sql, params);
	}

	@Override
	public List<TradiTea> getTeasByName(String teaName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("teaName", "%" + teaName + "%");
		
		String query = "SELECT 상품ID, 차이름, 제고수량, 제조일,"
				 +	   "용량, 가격, 설명 FROM 전통차 "
				 +     "where 차이름 like :teaName";
		
		List<TradiTea> result = jdbcTemplate.query(query, 
				params, new TradiTeaMapper());
		return result;
	}
}
