package mywork.myobjectpool;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.commons.pool2.*;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class MyObjectPoolFactory extends BasePooledObjectFactory<OSS> {

    @Override
    public OSS create() throws Exception {
        final String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        /**
         *∞¢¿Ô‘∆’À∫≈AccessKey
         */
        final String accessKeyId = "LTAI5t5yrEAECKttAs1WyCDt";
        final String accessKeySecret = "QactfiBenzjbVaPXcUa4LkCMo3I0t9";
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    @Override
    public PooledObject<OSS> wrap(OSS s) {
        return new DefaultPooledObject<OSS>(s);
    }
}
