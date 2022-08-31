package mywork.myobjectpool;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.util.StopWatch;

import java.time.Duration;

/**
 * 测试对象池
 *  相对于普通对象从对象池中获取，
 *  OOSClient 直接 new 反而更快
 */
public class MyTestObjPool {
    private static final int POOL_TIME = 100;
    public static void main(String[] args) throws Exception {
        testObjPool();
        testObj();
    }
    private static void testObjPool() throws Exception {
        MyObjectPoolFactory myObjectPoolFactory = new MyObjectPoolFactory();
        GenericObjectPoolConfig<OSS> genPoolConfig =
                new GenericObjectPoolConfig<OSS>();
        genPoolConfig.setMaxIdle(3000);
        genPoolConfig.setMinIdle(1000);
        genPoolConfig.setMaxWait(Duration.ofMillis(10000));

        MyObjectPool pool = new MyObjectPool(myObjectPoolFactory, genPoolConfig);


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < POOL_TIME; i++) {
            OSS ossClient = pool.borrowObject();

            pool.returnObject(ossClient);
        }
        stopWatch.stop();
        System.out.format("objPool:%d\n", stopWatch.getLastTaskTimeMillis());

    }


    private static void testObj() {
        final String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        /**
         *阿里云账号AccessKey
         */
        final String accessKeyId = "LTAI5t5yrEAECKttAs1WyCDt";
        final String accessKeySecret = "QactfiBenzjbVaPXcUa4LkCMo3I0t9";
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        OSS ossClient = null;
        for (int i = 0; i < POOL_TIME; i++) {
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            ossClient = null;
        }
        stopWatch.stop();
        System.out.format("obj:%d\n", stopWatch.getLastTaskTimeMillis());
    }


}
