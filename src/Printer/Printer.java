package Printer;
//Printer类

public class Printer{
	private String PrintName;
	private String PrintType;
	private String UserInfo;
	private String TaskInfo;
	private String Speed;
	
	//构造函数
	public Printer(String Printname, String Printtype, String Userinfo, String Taskinfo, String speed){
		PrintName = Printname;
		PrintType = Printtype;
		UserInfo = Userinfo;
		TaskInfo = Taskinfo;
		Speed = speed;
	}
	public Printer(){
		PrintName = null;
		PrintType = null;
		UserInfo = null;
		TaskInfo = null;
		Speed = null;
	}
	
	public void SetName(String Printname){
		PrintName = Printname;
	}
	public void SetType(String Printtype){
		PrintType = Printtype;
	}
	public void SetUser(String Userinfo){
		UserInfo = Userinfo;
	}
	public void SetTask(String Taskinfo){
		TaskInfo = Taskinfo;
	}
	public void SetSpeed(String speed){
		Speed = speed;
	}
	
	public String GetName(){
		return PrintName;
	}
	public String GetType(){
		return PrintType;
	}
	public String GetUser(){
		return UserInfo;
	}
	public String GetTask(){
		return TaskInfo;
	}
	public String GetSpeed(){
		return Speed;
	}
}
