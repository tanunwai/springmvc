package com.study.springmvc.lab.repository.impl;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.filtering;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.study.springmvc.lab.entity.Fund;
import com.study.springmvc.lab.entity.Fundstock;
import com.study.springmvc.lab.repository.FundstockDao;

@Repository
public class FundstockDaoImpl implements FundstockDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Fundstock> queryAll() {
		// String querySql="select s.sid, s.fid, s.symbol, s.share from fundstock s
		// order by s.sid";
		// return jdbcTemplate.query(querySql, new
		// BeanPropertyRowMapper<>(Fundstock.class));
		String querySql2 = "select s.sid, s.fid, s.symbol, s.share, f.fid as fund_fid, f.fname as fund_fname, f.createtime as fund_createtime from fundstock s left join fund f on f.fid=s.fid order by s.sid";
		ResultSetExtractor<List<Fundstock>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance().addKeys("sid")
				.newResultSetExtractor(Fundstock.class);
		return jdbcTemplate.query(querySql2, resultSetExtractor);
	}

	@Override
	public List<Fundstock> queryPage(int offset) {
		if (offset < 0) {
			return queryAll();
		}
		String sql = "select s.sid, s.fid, s.symbol, s.share, f.fid as fund_fid, f.fname as fund_fname, f.createtime as fund_createtime from fundstock s left join fund f on f.fid=s.fid order by s.sid";
		sql += String.format(" limit %d offset %d", FundstockDao.LIMIT, offset);// 串接每頁幾筆和總頁數
		ResultSetExtractor<List<Fundstock>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance().addKeys("sid")
				.newResultSetExtractor(Fundstock.class);
		return jdbcTemplate.query(sql, resultSetExtractor);
	}

	@Override
	public Fundstock get(Integer sid) {
		// 向左關聯並設定條件
		String sqlGet = "select s.sid, s.fid, s.symbol, s.share, f.fid as fund_fid, f.fname as fund_fname, f.createtime as fund_createtime from fundstock s left join fund f on f.fid=s.fid\r\n"
				+ "where s.sid=?\r\n" + " order by s.sid";
		// 建構RowMapper內部類別並實作其方法
		return jdbcTemplate.queryForObject(sqlGet, new Object[] { sid }, new RowMapper<Fundstock>() {

			@Override
			public Fundstock mapRow(ResultSet rs, int rowNum) throws SQLException {
				Fundstock fundstock = new Fundstock();
				fundstock.setSid(rs.getInt("s.sid"));
				fundstock.setFid(rs.getInt("s.fid"));
				fundstock.setSymbol(rs.getString("s.symbol"));
				fundstock.setShare(rs.getInt("s.share"));
				// 建構Fund物件並導入Fundstock物件
				Fund fund = new Fund();
				fund.setFid(rs.getInt("fund_fid"));
				fund.setFname(rs.getString("fund_fname"));
				fund.setCreatetime(rs.getDate("fund_createtime"));

				fundstock.setFund(fund);
				return fundstock;
			}
		});
	}

	@Override
	public int count() {
		String countSql = "select count(*) from fundstock";
		return jdbcTemplate.queryForObject(countSql, Integer.class);
	}

	@Override
	public int add(Fundstock fundstock) {
		String addSql = "insert into fundstock (fid, symbol, share) values(?,?,?)";
		int rowNum = jdbcTemplate.update(addSql, fundstock.getFid(), fundstock.getSymbol(), fundstock.getShare());
		return rowNum;
	}

	@Override
	public int update(Fundstock fundstock) {
		String updateSql = "update fundstock set fid=?, symbol=?, share=? where sid=?";
		int rowCount = jdbcTemplate.update(updateSql, fundstock.getFid(), fundstock.getSymbol(), fundstock.getShare(),
				fundstock.getSid());
		return rowCount;
	}

	@Override
	public int delete(Integer sid) {
		String deleteSql = "delete from fundstock where sid=?";
		int rowCount = jdbcTemplate.update(deleteSql, sid);
		return rowCount;
	}

	@Override
	public Map<String, Integer> getGroupMap() {
		return queryAll().stream().collect(groupingBy(Fundstock::getSymbol, filtering(p->p.getShare()!=null, summingInt(Fundstock::getShare)))).entrySet()
				.stream().sorted((v1, v2) -> v2.getValue().compareTo(v1.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (m1, m2) -> m2, LinkedHashMap::new));
	}
}