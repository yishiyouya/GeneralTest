package mywork.myobjectpool2;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.util.StopWatch;

import java.time.Duration;

/**
 * ≤‚ ‘∂‘œÛ≥ÿ
 */
public class MyTestObjPool {

    private static final int POOL_TIME = 100;
    public static void main(String[] args) throws Exception {
        testObjPool();
        testObj();
    }
    private static void testObjPool() throws Exception {
        MyObjectPoolFactory myObjectPoolFactory = new MyObjectPoolFactory();
        GenericObjectPoolConfig<User> genPoolConfig =
                new GenericObjectPoolConfig<User>();
        genPoolConfig.setMaxIdle(3000);
        genPoolConfig.setMinIdle(1000);
        genPoolConfig.setMaxWait(Duration.ofMillis(10000));

        MyObjectPool pool = new MyObjectPool(myObjectPoolFactory, genPoolConfig);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < POOL_TIME; i++) {
            User user = pool.borrowObject();

            pool.returnObject(user);
        }

        stopWatch.stop();
        System.out.format("objPool:%d\n", stopWatch.getLastTaskTimeMillis());
    }


    private static void testObj() {
        final String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        /**
         *∞¢¿Ô‘∆’À∫≈AccessKey
         */
        final String accessKeyId = "LTAI5t5yrEAECKttAs1WyCDt";
        final String accessKeySecret = "QactfiBenzjbVaPXcUa4LkCMo3I0t9";
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        User user = null;
        for (int i = 0; i < POOL_TIME; i++) {
            user = new User("a");
            user = null;
        }
        stopWatch.stop();
        System.out.format("obj:%d\n", stopWatch.getLastTaskTimeMillis());
    }


}
