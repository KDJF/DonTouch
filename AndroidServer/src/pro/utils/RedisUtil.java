package pro.utils;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	public static void main(String[] args) {
	      //���ӱ��ص� Redis ����
	      Jedis jedis = new Jedis("localhost");
	      System.out.println("Connection to server sucessfully");
	      //�鿴�����Ƿ�����
	      if (!jedis.exists("name")){
	    	  
	      }else{
	    	  
	      }
	 }
	
	public static Jedis getJedis(){
		Jedis jedis = new Jedis("localhost");
	      System.out.println("Connection to server sucessfully");
	      //�鿴�����Ƿ�����
	      System.out.println("Server is running: "+jedis.ping());
		return jedis;
	}
}
