package com.nwnu.yiqing.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nwnu.yiqing.entity.Teacher;

public class TeacherDao extends BaseDao<Teacher>{
	/**
	 * 根据用户名查找用户信息
	 * @param name
	 * @return
	 */
	public Teacher getTeacher(String name){
		Teacher teacher = null;
		String sql = "select * from admin where name = '" + name + "'";
		try {
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			ResultSet executeQuery = prepareStatement.executeQuery();
			if(executeQuery.next()){
				teacher = new Teacher();
				teacher.setId(executeQuery.getInt("id"));
				teacher.setName(executeQuery.getString("name"));
				teacher.setPassword(executeQuery.getString("password"));
				//student.setStatus(executeQuery.getInt("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher;
	}
}
