package pro.utils;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	
	public static Jedis getJedis(){
		Jedis jedis = new Jedis("localhost");
	      System.out.println("Connection to redis server sucessfully");
	      //�鿴�����Ƿ�����
	      System.out.println("Server is running: "+jedis.ping());
		return jedis;
	}
}
