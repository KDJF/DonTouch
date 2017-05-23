package pro.service.serviceImpl;

import java.util.ArrayList;

import pro.service.UserService;
import pro.utils.RedisUtil;
import redis.clients.jedis.Jedis;

public class UserServiceImpl implements UserService{
	private Jedis jedis = null;

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
		jedis = RedisUtil.getJedis();
	}

	@Override
	public String register(String name, String passwd) {
		if (jedis.exists(name+":name")) {
			return "fail:username has existed";
		} else {
			jedis.set(name+":name", name);
			jedis.set(name+":passwd", passwd);
			return "success";
		}
	}
	
	@Override
	public String login(String name,String passwd){
		if(!jedis.exists(name+":name")){
			return "fail:username is not exist";
		}else if(jedis.exists(name+":name") && !jedis.get(name + ":passwd").equals(passwd)){
			return "fail:erro password";
		}else{
			return "success";
		}
	}

	@Override
	public String savawhitelist(String name, ArrayList<String> whitelist) {
		// TODO Auto-generated method stub
		if(!jedis.exists(name+":name")){
			return "fail:username is not exist";
		}else if(whitelist == null){
			return "fail:empty list";
		}else{
			for (int i = 0; i < whitelist.size(); i++) {
				jedis.lpush(name+":white-list", whitelist.get(i));
			}
			return "success";
		}
		
	}

}
