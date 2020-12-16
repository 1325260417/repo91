package com.yc.dyg.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yc.dyg.bean.DygCategory;
import com.yc.dyg.dao.DygDao;

@WebServlet("/queryCyg.s")
public class QueryCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private DygDao dao = new DygDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决乱码问题
		response.setContentType("text/html;charset=utf-8");
		List<DygCategory> list = null;
		try {
			list = dao.selectAllcategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//list ==> json 使用gson
		//[ {...},{...},{...},{...}...]
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.getWriter().append(json);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
