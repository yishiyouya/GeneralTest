package mywork.myjavaapi;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import mywork.myjavaapi.anaSubPush.SubPushValue;
import mywork.mymysql.MyConst;
import mywork.mymysql.MyMysqlPersistUtil;

/**
 * ���ݳ־û�������
 *
 */
public class MyDataPersisUtil {

    private static MyMysqlPersistUtil mysqlPersistUtil = MyMysqlPersistUtil.getInstance();
    
    //�־û���mysql 
    public static void insert(String dbType, String kindType, String sql, List subPushs) throws Exception {
        if (null != dbType && dbType.equals(MyConst.DB_MYSQL)) {
            if (MyConst.KIND_SUB_PUSH.equals(kindType)) {
                mysqlPersistUtil.insert(sql, subPushs);
            } else if (MyConst.KIND_SUB_SCRIPT.equals(kindType)) {
                mysqlPersistUtil.insertSubScript(sql, subPushs);
            }
        }
    }

    //�־û���mongoDB
    public static void persisToMongoDB(){
        
    }
    
}
