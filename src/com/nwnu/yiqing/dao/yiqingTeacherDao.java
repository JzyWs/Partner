package com.nwnu.yiqing.dao;

import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.nwnu.yiqing.entity.yiqingTeacher;

public class yiqingTeacherDao extends BaseDao<yiqingTeacher> {
	/**
	 * 学院负责人提交本学院学生情况时修改本学院学生的state（是否已审核）属性
	 */
	public boolean updateTeacheryiqing(String College){
		String sql = " update yiqing_teacher set state ='已审核' where college='"+College+"' ";
		try {
			PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sql);
			return prepareStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
