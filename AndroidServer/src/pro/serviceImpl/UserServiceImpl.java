package pro.serviceImpl;

import pro.serviceinterface.UserService;
import pro.utils.RedisUtil;
import redis.clients.jedis.Jedis;

public class UserServiceImpl implements UserService{
	private Jedis jedis = null;

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
		jedis = RedisUtil.getJedis();
	}

	public String register(String name, String passwd) {
		if (jedis.exists(name+":name")) {
			return "fail:username has existed";
		} else {
			jedis.set(name+":name", name);
			jedis.set(name+":passwd", passwd);
			return "success";
		}
	}
	
	public String login(String name,String passwd){
		if(!jedis.exists(name+":name")){
			return "fail:username is not exist";
		}else if(jedis.exists(name+":name") && !jedis.get(name + ":passwd").equals(passwd)){
			return "fail:erro password";
		}else{
			return "success";
		}
	}

}
