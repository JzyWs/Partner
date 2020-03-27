package yqToexcel;

import java.sql.Date;

public class Yiq {
	private int id;
	private String name;
	private String college;
	private String class1;
	private Date date;
	private String place;
	private String wuhan;
	private String hubei;
	private String wuhancontact;
	private String hubeicontact;
	private String back;
	private String suspected;
	private String confirm;
	private String state;
	
	public Yiq(int id, String name, String college, String class1,Date date, String place, String wuhan, String hubei,
			String wuhancontact, String hubeicontact, String back, String suspected, String confirm,
			String state) {
		// TODO 自动生成的构造函数存根
		this.id = id;
        this.name = name;
        this.college = college;
        this.class1 = class1;
        this.date = date;
        this.place = place;
        this.wuhan = wuhan;
        this.hubei = hubei;
        this.wuhancontact = wuhancontact;
        this.hubeicontact = hubeicontact;
        this.back = back;
        this.suspected = suspected;
        this.confirm = confirm;
        this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName1() {
		return name;
	}
	public void setName1(String name1) {
		this.name = name1;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getClass1() {
		return class1;
	}
	public void setClass1(String class1) {
		this.class1 = class1;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getWuhan() {
		return wuhan;
	}
	public void setWuhan(String wuhan) {
		this.wuhan = wuhan;
	}
	public String getHubei() {
		return hubei;
	}
	public void setHubei(String hubei) {
		this.hubei = hubei;
	}
	public String getWuhancontact() {
		return wuhancontact;
	}
	public void setWuhancontact(String wuhancontact) {
		this.wuhancontact = wuhancontact;
	}
	public String getHubeicontact() {
		return hubeicontact;
	}
	public void setHubeicontact(String hubeicontact) {
		this.hubeicontact = hubeicontact;
	}
	public String getBack() {
		return back;
	}
	public void setBack(String back) {
		this.back = back;
	}
	public String getSuspected() {
		return suspected;
	}
	public void setSuspected(String suspected) {
		this.suspected = suspected;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String toString() {
		return id + " " + name + " " + college + " " + class1 + " " + date + " " + place + " " + wuhan + " " + hubei
				+ " " + wuhancontact + " " + hubeicontact + " " + back + " " + suspected + " " + confirm + " " + state;
	}
}
