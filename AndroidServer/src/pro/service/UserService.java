package pro.service;

import java.util.ArrayList;

public interface UserService {
	//×¢²á
	public String register(String name, String passwd);
	//µÇÂ½
	public String login(String name,String passwd);
	//´¢´æ°×Ãûµ¥
	public String savawhitelist(String name, ArrayList<String> whitelist);
}
