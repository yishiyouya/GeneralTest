package mywork.myredis;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class MyTestJedis {
    
    
    public static void main(String[] args) {
        jedisPing();
    }
    
    public static void jedisPing(){
        Jedis jedis = getJedis();
        String ping = jedis.ping("1.1.1.1");
        System.out.println(ping);
    }
    
    public static void getQuoDataKeys(){
        Jedis jedis = getJedis();
        jedis.select(1);
        Set<String> quoDataSet = jedis.keys("STATIC:CORE-SERVICES:*");
        if (quoDataSet.isEmpty()) {
            quoDataSet = new HashSet<String>();
        }

        for (String fileName : quoDataSet) {
            //192.168.4.69:1628 192.168.4.35:1628
            String value = jedis.smembers(fileName).toString();
            String replicate = "";//jedis.info(RedisConfigConst.RedisInfoReplication);
            replicate += "redisHost:"+jedis.getClient().getHost();
            replicate += "\nredisPort:"+jedis.getClient().getPort();
            int index = fileName.indexOf(":CORE-SERVICES:");
            String key = fileName.substring(index + ":CORE-SERVICES:".length());
            System.out.format("%s, %s\n", key, value);
        }
        quoDataSet.clear();
        quoDataSet = null;
    }
    
    public static Jedis getJedis(){
        MyRedisConnect myRedisCon = MyRedisConnect.getInstance();
        Jedis jedis = myRedisCon.redisConnect();
        return jedis;
    }
    
    public static void getJedisKeys(Jedis jedis){
        String key = "DYNAMIC:MASTER_SLAVES:CORE-SERVICES:****";
        jedis.select(1);
        Set<String> keySet = jedis.keys(key);
        String redisHost = jedis.getClient().getHost();
        int redisPort = jedis.getClient().getPort();
        System.out.format("%s:%d\n", redisHost, redisPort);
        
        jedis.info("Replication");
        System.out.println(keySet);
    }
    
    
    
}
