package mywork.myobjectpool2;

import com.aliyun.oss.OSS;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class MyObjectPool extends GenericObjectPool<User> {
    public MyObjectPool(PooledObjectFactory<User> factory) {
        super(factory);
    }

    public MyObjectPool(PooledObjectFactory<User> factory, GenericObjectPoolConfig<User> config) {
        super(factory, config);
    }

    public MyObjectPool(PooledObjectFactory<User> factory, GenericObjectPoolConfig<User> config, AbandonedConfig abandonedConfig) {
        super(factory, config, abandonedConfig);
    }
}
