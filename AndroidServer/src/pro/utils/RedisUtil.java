package pro.utils;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	public static void main(String[] args) {
	      //连接本地的 Redis 服务
	      Jedis jedis = new Jedis("localhost");
	      System.out.println("Connection to server sucessfully");
	      //查看服务是否运行
	      if (!jedis.exists("name")){
	    	  
	      }else{
	    	  
	      }
	 }
	
	public static Jedis getJedis(){
		Jedis jedis = new Jedis("localhost");
	      System.out.println("Connection to server sucessfully");
	      //查看服务是否运行
	      System.out.println("Server is running: "+jedis.ping());
		return jedis;
	}
}
