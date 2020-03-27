package yqToexcel;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

//实现Excel导入数据库核心类，读取Excel表中所有的数据，操作数据（查询、更新）
public class YiqService {
	/**
	 * 查询数据库中yiqing表中所有的数据
	 */
	public static List<Yiq> getAllByDb() {
		List<Yiq> list = new ArrayList<Yiq>();
		DBhelper db = new DBhelper();
		String sql = "select d.id,d.name1,d.college,d.class1,d.date,d.place,d.wuhan,d.hubei,d.wuhancontact,d.hubeicontact,d.back,d.suspected,d.confirm,d.state from yiqing d";
		ResultSet rs = db.Search(sql, null);
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name1 = rs.getString("name1");
				String college = rs.getString("college");
				String class1 = rs.getString("class1");
				Date date = rs.getDate("date");
				String place = rs.getString("place");
				String wuhan = rs.getString("wuhan");
				String hubei = rs.getString("hubei");
				String wuhancontact = rs.getString("wuhancontact");
				String hubeicontact = rs.getString("hubeicontact");
				String back = rs.getString("back");
				String suspected = rs.getString("suspected");
				String confirm = rs.getString("confirm");
				String state = rs.getString("state");
				
				list.add(new Yiq(id,name1,college,class1,date,place,wuhan,hubei,wuhancontact,hubeicontact,back,suspected,confirm,state));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	

}
