package com.nwnu.yiqing.servlet;

import java.io.File;
import jxl.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import net.sf.json.JSONObject;

import com.nwnu.yiqing.bean.Operator;
import com.nwnu.yiqing.bean.Page;
import com.nwnu.yiqing.bean.SearchProperty;
import com.nwnu.yiqing.dao.SchoolOfficeDao;
import com.nwnu.yiqing.dao.StudentDao;
import com.nwnu.yiqing.dao.yiqingDao;
import com.nwnu.yiqing.dao.yiqingTeacherDao;
import com.nwnu.yiqing.dao.SchoolOfficeDao;
import com.nwnu.yiqing.entity.Collegeadmin;
import com.nwnu.yiqing.entity.SchoolOffice;
import com.nwnu.yiqing.entity.Student;
import com.nwnu.yiqing.entity.yiqing;
import com.nwnu.yiqing.entity.yiqingTeacher;
import com.nwnu.yiqing.entity.SchoolOffice;
import com.nwnu.yiqing.util.StringUtil;

public class SchoolOfficeServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5553783024898694511L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		if("toschoolListView".equals(method)){
			req.getRequestDispatcher("view/schoolList.jsp").forward(req, resp);
		}
		if("toAllteacherListView".equals(method)){
			req.getRequestDispatcher("view/AllteacherList.jsp").forward(req, resp);
		}
		if("AllList".equals(method)){
			getAllList(req,resp);
		}
		if("AllteacherList".equals(method)){
			getAllteacherList(req,resp);
		}
	}

	/**
	 * 从数据库yiqing表中获取已经被学校负责人提交的学生记录
	 */
	private void getAllList(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		int pageNumber = Integer.parseInt(req.getParameter("page"));
		int pageSize = Integer.parseInt(req.getParameter("rows"));
		String college = req.getParameter("college");
		if(StringUtil.isEmpty(college)){
			college = "";
		}
		Map<String, Object> ret = new HashMap<String, Object>();
		yiqingDao Dao = new yiqingDao();
		Page<yiqing> page = new Page<yiqing>(pageNumber, pageSize);
		page.getSearchProperties().add(new SearchProperty("state", "已审核" , Operator.EQ));
		Page<yiqing> findList = Dao.findList(page);
		ret.put("rows", findList.getConten());
		ret.put("total", findList.getTotal());
				
		Dao.closeConnection();
		resp.setCharacterEncoding("utf-8");
		try {
			resp.getWriter().write(JSONObject.fromObject(ret).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/**
 * 从数据库yiqing表中获取已经被学校负责人提交的老师记录
 */
private void getAllteacherList(HttpServletRequest req, HttpServletResponse resp) {
	// TODO Auto-generated method stub
	int pageNumber = Integer.parseInt(req.getParameter("page"));
	int pageSize = Integer.parseInt(req.getParameter("rows"));
	String college = req.getParameter("college");
	if(StringUtil.isEmpty(college)){
		college = "";
	}
	Map<String, Object> ret = new HashMap<String, Object>();
	yiqingTeacherDao Dao = new yiqingTeacherDao();
	Page<yiqingTeacher> page = new Page<yiqingTeacher>(pageNumber, pageSize);
	page.getSearchProperties().add(new SearchProperty("state", "已审核" , Operator.EQ));
	Page<yiqingTeacher> findList = Dao.findList(page);
	ret.put("rows", findList.getConten());
	ret.put("total", findList.getTotal());
			
	Dao.closeConnection();
	resp.setCharacterEncoding("utf-8");
	try {
		resp.getWriter().write(JSONObject.fromObject(ret).toString());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
