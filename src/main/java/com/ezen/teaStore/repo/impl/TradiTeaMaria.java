package com.ezen.teaStore.repo.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ezen.teaStore.domain.TradiTea;
import com.ezen.teaStore.repo.TradiTeaRepo;

//@formatter:off
@Repository
public class TradiTeaMaria implements TradiTeaRepo {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<TradiTea> getAllTradiTeas() {
		var params = new HashMap<String, Object>();
		String query = SELECT_PREFIX;
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
			tradiTea.setProdDesc(rs.getString(idx++));
			tradiTea.setPrice(rs.getDouble(idx++));
			tradiTea.setPriceOrignal(rs.getDouble(idx++));
			return tradiTea;
		}
	}

	@Override
	public List<String> getTEA_COUNT() {
		var params = new HashMap<String, Object>();
		String query = "SELECT 차이름 FROM 전통차";
		var nameList= jdbcTemplate.queryForList(
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
		
		sql = "update 전통차 set 가격=가격_원, 가격_원 = null "
				+ "where 차이름 != :todayTea and 가격 != 가격_원 "
				+ "and 가격_원 is not null";
		jdbcTemplate.update(sql, params);
	}

	@Override
	public List<TradiTea> getTeasByName(String teaName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("teaName", "%" + teaName + "%");
		
		String query = SELECT_PREFIX + "where 차이름 like :teaName";
		List<TradiTea> result = jdbcTemplate.query(query, 
				params, new TradiTeaMapper());
		return result;
	}

	private static final String SELECT_PREFIX = 
			"SELECT 상품ID, 차이름, 제고수량, 제조일, 용량, 설명, 가격, 가격_원 FROM 전통차 ";
	
	@Override
	public List<TradiTea> getByNamePrice(String teaName, 
			Map<String, String> price) {
		var sb = new StringBuilder(SELECT_PREFIX);
		sb.append("where 차이름 like :teaName ");
		sb.append("and 가격 >= :low and 가격 <= :high");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("teaName", "%" + teaName + "%");
		params.put("low", price.get("low"));
		params.put("high", price.get("high"));
		
		List<TradiTea> result = jdbcTemplate.query(sb.toString(), 
				params, new TradiTeaMapper());
		
		return result;
	}

	@Override
	public TradiTea getTradiTea(int teaId) {
		var sb = new StringBuilder(SELECT_PREFIX);
		sb.append("where 상품ID = :teaId  ");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("teaId", teaId);
		
		TradiTea result = jdbcTemplate.queryForObject(
				sb.toString(), params, new TradiTeaMapper());
		
		return result;
	}

	@Override
	public void addTradiTea(TradiTea tradiTea) {
		var sp = new StringBuffer();
		sp.append("insert into 전통차 ");
		sp.append("(차이름, 제고수량, 제조일, 용량, 가격, 설명) ");
		sp.append("values (:teaName, :stock, :prodDate, :amount, ");
		sp.append(":price, :prodDesc)");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("teaName", tradiTea.getTeaName());  
		params.put("stock", tradiTea.getStock());  
		params.put("prodDate", tradiTea.getProdDate());  
		params.put("amount", tradiTea.getAmount());  
		params.put("price", tradiTea.getPrice());  
		params.put("prodDesc", tradiTea.getProdDesc());  
		
		jdbcTemplate.update(sp.toString(), params); 
		
	}	
}
