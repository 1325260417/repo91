package com.yc.dyg.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.yc.dyg.bean.DygCategory;
import com.yc.dyg.bean.DygMovie;
import com.yc.dyg.bean.DygMovieWithBLOBs;
import com.yc.dyg.util.DBHelper;
import com.yc.dyg.util.DBHelper.ResultSetMapper;

public class DygTest {

	/**
	 * 单元测试，代替main方法测试
	 * @throws SQLException
	 */
	@Test //注解
	public void selectAllcategory() throws SQLException {
		String sql = "select * from dyg_category";
		List<DygCategory> list;
		list = DBHelper.selectList(sql, new ResultSetMapper<DygCategory>() {

			@Override
			public DygCategory map(ResultSet rs) throws SQLException {
				DygCategory dc = new DygCategory();
				dc.setId(rs.getInt("id"));
				dc.setName(rs.getString("name"));
				dc.setSn(rs.getString("sn"));
				return dc;
			}
		});
		
		for(DygCategory dc : list) {
			System.out.println(dc.getName());
		}
	}
	
	@Test //注解
	public void selectIndexYs() throws SQLException {
		String sql = "select * from dyg_movie where category='ys'"
				+ " order by create_date desc limit 0,16";
		List<DygMovie> list;
		list = DBHelper.selectList(sql, new ResultSetMapper<DygMovie>() {

			@Override
			public DygMovie map(ResultSet rs) throws SQLException {
				DygMovie dm = new DygMovie();
				dm.setId(rs.getInt("id"));
				dm.setName(rs.getString("name"));
				dm.setPoster(rs.getString("poster"));
				dm.setCreateDate(rs.getInt("create_date"));
				//其他字段请自行添加
				return dm;
			}
		});
		
		for(DygMovie dm : list) {
			System.out.println(dm.getPoster()+"--"+dm.getName());
		}
	}
	
	@Test //注解
	public void selectById() throws SQLException {
		String sql = "select * from dyg_movie where id=?";
		List<DygMovieWithBLOBs> list;
		String id="45582";
		list = DBHelper.selectList(sql, new ResultSetMapper<DygMovieWithBLOBs>() {

			@Override
			public DygMovieWithBLOBs map(ResultSet rs) throws SQLException {
				DygMovieWithBLOBs dm = new DygMovieWithBLOBs();
				dm.setId(rs.getInt("id"));
				dm.setName(rs.getString("name"));
				dm.setPoster(rs.getString("poster"));
				dm.setCreateDate(rs.getInt("create_date"));
				dm.setIntro(rs.getString("intro"));
				//其他字段请自行添加
				return dm;
			}
		},id);
		
		for(DygMovieWithBLOBs dm : list) {
			System.out.println(dm.getPoster()+"--"+dm.getName());
		}
	}
}
