package mywork.myobjectpool;

import com.aliyun.oss.OSS;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class MyObjectPool extends GenericObjectPool<OSS> {
    public MyObjectPool(PooledObjectFactory<OSS> factory) {
        super(factory);
    }

    public MyObjectPool(PooledObjectFactory<OSS> factory, GenericObjectPoolConfig<OSS> config) {
        super(factory, config);
    }

    public MyObjectPool(PooledObjectFactory<OSS> factory, GenericObjectPoolConfig<OSS> config, AbandonedConfig abandonedConfig) {
        super(factory, config, abandonedConfig);
    }
}
