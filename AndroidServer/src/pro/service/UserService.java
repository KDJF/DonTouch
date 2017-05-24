package pro.service;

import java.util.ArrayList;

public interface UserService {
	//注册
	public String register(String name, String passwd);
	//登陆
	public String login(String name,String passwd);
	//储存白名单
	public String savawhitelist(String name, ArrayList<String> whitelist);
	//读取白名单
	public ArrayList<String> readwhitelist(String name);
}
