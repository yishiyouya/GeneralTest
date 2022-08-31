package mywork.myobjectpool2;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class MyObjectPoolFactory extends BasePooledObjectFactory<User> {

    @Override
    public User create() throws Exception {
        return new User("a");
    }

    @Override
    public PooledObject<User> wrap(User s) {
        return new DefaultPooledObject<User>(s);
    }
}
