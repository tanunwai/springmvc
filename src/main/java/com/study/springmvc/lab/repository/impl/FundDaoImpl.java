package com.study.springmvc.lab.repository.impl;

import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.study.springmvc.lab.entity.Fund;
import com.study.springmvc.lab.entity.Fundstock;
import com.study.springmvc.lab.repository.FundDao;

@Repository
public class FundDaoImpl implements FundDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Fund> queryAll() {
		return queryAllCase3();
	}

	private List<Fund> queryAllCase1() {
		String querySql = "select f.fid, f.fname, f.createtime from fund f order by f.fid";
		return jdbcTemplate.query(querySql, new BeanPropertyRowMapper<>(Fund.class));
	}

	private List<Fund> queryAllCase2() {
		String querySql = "select f.fid, f.fname, f.createtime from fund f order by f.fid";
		return jdbcTemplate.query(querySql, (rs, rowNum) -> {
			Fund fund = new Fund();
			fund.setFid(rs.getInt("f.fid"));
			fund.setFname(rs.getString("f.fname"));
			fund.setCreatetime(rs.getDate("f.createtime"));
			// 根據fid查詢funckstock列表
			String querySql2 = "select s.sid, s.fid, s.symbol, s.share from fundstock s where s.fid=? order by s.sid";
			fund.setFundstocks(jdbcTemplate.query(querySql2, new Object[] { fund.getFid() },
					new BeanPropertyRowMapper<>(Fundstock.class)));
			return fund;
		});
	}

	// 調用 org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory
	private List<Fund> queryAllCase3() {
		// fundstocks_sid 其中 fundstocks 指的是 Fund.java 一對多的屬性命名
		String querySql = "select f.fid, f.fname, f.createtime,\r\n"
				+ "s.sid as fundstocks_sid, s.fid as fundstocks_fid, s.symbol as fundstocks_symbol, s.share as fundstocks_share\r\n"
				+ "from fund f left join fundstock s\r\n" + "on f.fid = s.fid";
		ResultSetExtractor<List<Fund>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance().addKeys("fid")
				.newResultSetExtractor(Fund.class);
		return jdbcTemplate.query(querySql, resultSetExtractor);
	}

	@Override
	public List<Fund> queryPage(int offset) {//因為會遺失查尋比數故使用此方式:參照queryPage2
		if (offset < 0) {
			return queryAll();
		}
		String querySql = "select f.fid, f.fname, f.createtime from fund f order by f.fid";
		querySql += String.format(" limit %d offset %d", FundDao.LIMIT, offset);
		RowMapper<Fund> rm = (rs, rowNum) -> {
			Fund fund = new Fund();
			fund.setFid(rs.getInt("fid"));
			fund.setFname(rs.getString("fname"));
			fund.setCreatetime(rs.getDate("createtime"));
			// 根據fid查詢fundstock
			String querySql2 = "select s.sid, s.fid, s.symbol, s.share from fundstock s where s.fid=? order by s.sid";
			Object[] args = { fund.getFid() };
			List<Fundstock> fundstocks = jdbcTemplate.query(querySql2, args,
					new BeanPropertyRowMapper<Fundstock>(Fundstock.class));
			fund.setFundstocks(fundstocks);
			return fund;
		};

		return jdbcTemplate.query(querySql, rm);
	}
	
	@Override
	public List<Fund> queryPage2(int offset){
		if(offset < 0) {
			return queryAll();
		}
		String sql="select f.fid, f.fname, f.createtime,\r\n"
				+ "s.sid as fundstocks_sid, s.fid as fundstocks_fid,\r\n"
				+ "s.symbol as fundstocks_symbol, s.share as fundstocks_share \r\n"
				+ "from fund f left join fundstock s on s.fid=f.fid";
				sql+=String.format(" order by f.fid limit %d offset %d", FundDao.LIMIT, offset);
		ResultSetExtractor<List<Fund>> resultSetExtractor=JdbcTemplateMapperFactory.newInstance().addKeys("fid").newResultSetExtractor(Fund.class);
		return jdbcTemplate.query(sql, resultSetExtractor);
	}

	@Override
	public Fund get(Integer fid) {
		String sql = "select f.fid, f.fname, f.createtime from fund f where f.fid=?";
		Fund fund = jdbcTemplate.queryForObject(sql, new Object[] { fid }, new BeanPropertyRowMapper<Fund>(Fund.class));
		String sql2 = "select s.sid, s.fid, s.symbol, s.share from fundstock s where s.fid=?";
		List<Fundstock> fundstocks = jdbcTemplate.query(sql2, new Object[] { fund.getFid() },
				new BeanPropertyRowMapper<Fundstock>(Fundstock.class));
		fund.setFundstocks(fundstocks);
		return fund;
	}

	@Override
	public int count() {
		String sql = "select count(*) from fund";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int add(Fund fund) {
		String sql = "insert into fund (fname) values(?)";
		return jdbcTemplate.update(sql, fund.getFname());
	}

	@Override
	public int update(Fund fund) {
		String sql = "update fund set fname=? where fid=?";
		return jdbcTemplate.update(sql, fund.getFname(), fund.getFid());
	}

	@Override
	public int delete(Integer fid) {
		String sql = "delete from fund where fid=?";
		return jdbcTemplate.update(sql, fid);
	}
}