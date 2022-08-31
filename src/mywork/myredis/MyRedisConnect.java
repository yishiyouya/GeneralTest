package mywork.myredis;

import redis.clients.jedis.Jedis;

public class MyRedisConnect {

    private static Jedis jedis = null;

    private MyRedisConnect() {

    }

    private static class MyRedisConnectCreator {
        private static final MyRedisConnect instance = new MyRedisConnect();
    }

    public static MyRedisConnect getInstance() {
        return MyRedisConnectCreator.instance;
    }

    public Jedis redisConnect() {
        /*String host = "192.168.4.51";
        int port = 1628;*/
        String host = "127.0.0.1";
        int port = 1628;
        String auth = "111234qwerasdf";
        //CONFIG SET requirepass "111234qwerasdf"
        if (null == jedis) {
            jedis = new Jedis(host, port);
            jedis.auth(auth);
        }
        return jedis;
    }

}
