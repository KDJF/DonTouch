package pro.service;

import java.util.ArrayList;

public interface UserService {
	//ע��
	public String register(String name, String passwd);
	//��½
	public String login(String name,String passwd);
	//���������
	public String savawhitelist(String name, ArrayList<String> whitelist);
}
