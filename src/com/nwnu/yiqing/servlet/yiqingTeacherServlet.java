package com.nwnu.yiqing.servlet;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import  com.nwnu.yiqing.bean.Operator;
import  com.nwnu.yiqing.bean.Page;
import  com.nwnu.yiqing.bean.SearchProperty;
import  com.nwnu.yiqing.dao.yiqingDao;
import com.nwnu.yiqing.dao.yiqingTeacherDao;
import com.nwnu.yiqing.entity.Collegeadmin;
import com.nwnu.yiqing.entity.Student;
import  com.nwnu.yiqing.entity.yiqing;
import com.nwnu.yiqing.entity.yiqingTeacher;
import  com.nwnu.yiqing.util.StringUtil;
import javax.servlet.http.HttpServletRequest;
public class yiqingTeacherServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1028698240374315446L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		if("toTeacherListView".equals(method)){
			req.getRequestDispatcher("view/teacherList.jsp").forward(req, resp);
		}
		if("Addyiqing".equals(method)){
			try {
				addyiqing(req,resp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	 /**
	  * 从JSP获取表单中的值并将记录插入数据库
     */

	private void addyiqing(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
		// TODO Auto-generated method stub
		String name = new String(req.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		//System.out.println("name3"+name);
		String id = new String(req.getParameter("id").getBytes("ISO-8859-1"),"UTF-8");
		int id2=Integer.parseInt(id);//将String转化为int
		String college = new String(req.getParameter("college").getBytes("ISO-8859-1"),"UTF-8");
		String date = new String(req.getParameter("date").getBytes("ISO-8859-1"),"UTF-8");
		String place = new String(req.getParameter("place").getBytes("ISO-8859-1"),"UTF-8");
		String wuhan = new String(req.getParameter("wuhan").getBytes("ISO-8859-1"),"UTF-8");
		String hubei = new String(req.getParameter("hubei").getBytes("ISO-8859-1"),"UTF-8");
		String wuhancontact = new String(req.getParameter("wuhancontact").getBytes("ISO-8859-1"),"UTF-8");
		String hubeicontact = new String(req.getParameter("hubeicontact").getBytes("ISO-8859-1"),"UTF-8");
		String suspected = new String(req.getParameter("suspected").getBytes("ISO-8859-1"),"UTF-8");
		String confirm = new String(req.getParameter("confirm").getBytes("ISO-8859-1"),"UTF-8");
		resp.setCharacterEncoding("utf-8");
		
		yiqingTeacher teacher = new yiqingTeacher();	
		teacher.setId(id2);
		teacher.setName1(name);
		teacher.setCollege(college);
		teacher.setDate(date);
		teacher.setPlace(place);
		teacher.setWuhan(wuhan);
		teacher.setHubei(hubei);
		teacher.setWuhancontact(wuhancontact);
		teacher.setHubeicontact(hubeicontact);
		teacher.setSuspected(suspected);
		teacher.setConfirm(confirm);
		yiqingTeacherDao studentDao = new yiqingTeacherDao();
		String msg = "你今天已经提交过了，不可重复提交!";		
		/*判断时间是否在每天十点以前*/
		//从系统获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	String now =df.format(new Date());	
    	
    	//从系统获取是哪一天
    	SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd ");
    	String start =df2.format(new Date());
    	
    	//从系统获取是哪一天
    	SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd ");
    	String end =df3.format(new Date());
		String format = "yyyy-MM-dd HH:mm";
        Date nowTime = new SimpleDateFormat(format).parse(now);
        Date startTime = new SimpleDateFormat(format).parse(start+"00:00");
        Date endTime = new SimpleDateFormat(format).parse(end+"23:00");
        System.out.println(isEffectiveDate(nowTime, startTime, endTime));
        if(isEffectiveDate(nowTime, startTime, endTime)){
        	if(studentDao.add(teacher)){
    			msg = "提交成功";
    		}
        }
        else {
        	    msg="请于每天十点填写！";
        }
		studentDao.closeConnection();
		resp.getWriter().write(msg);
	}
	
	
	 /**
           * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     * 
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }
        
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}

