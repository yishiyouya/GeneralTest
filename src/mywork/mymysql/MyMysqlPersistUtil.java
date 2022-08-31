package mywork.mymysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mywork.myjavaapi.anaSubPush.LoadSubPushUtil;
import mywork.myjavaapi.anaSubPush.SubPushValue;
import mywork.myjavaapi.anaSubPush.SubscriptValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyMysqlPersistUtil {
    
    private static Logger log = LoggerFactory.getLogger(MyMysqlPersistUtil.class);
    
    private static class MyMysqlPersistUtilProducer {
        private static MyMysqlPersistUtil instance = new MyMysqlPersistUtil();
    }
    
    private MyMysqlPersistUtil(){
        
    }
    
    public static MyMysqlPersistUtil getInstance(){
        return MyMysqlPersistUtilProducer.instance;
    }
    
    
    private static Connection getConnection() throws Exception {
        Connection conn = null;
        try {
            Class.forName(MyConst.MYSQL_JDBC_DRIVER);
            conn = DriverManager.getConnection(MyConst.MYSQL_URL, MyConst.MYSQL_USER, MyConst.MYSQL_PSW);
        } catch (Exception e) {
            log.error("create mysql conn failed!", e);
            throw e;
        }
        return conn;
    }
    
    public static void insert(String sql, List<SubPushValue> subPushs) throws Exception {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstm = conn.prepareStatement(sql);
            String[] msgDArr = new String[5];
            for (SubPushValue subPushValue : subPushs) {
                pstm.setString(1, subPushValue.getAcctId());
                pstm.setString(2, subPushValue.getClientSerialNum());
                pstm.setString(3, subPushValue.getSenderSubId());
                if (null != subPushValue.getMessageData()) {
                    msgDArr = subPushValue.getMessageData().split(LoadSubPushUtil.sep);
                }
                pstm.setString(4, msgDArr[0]);
                pstm.setString(5, msgDArr[1]);
                pstm.setString(6, msgDArr[4]);
                pstm.addBatch();
            }
            pstm.executeBatch();
            conn.commit();
        } catch (Exception e) {
            log.error("insert mysql failed!", e);
            throw e;
        } finally {
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    log.error("close pstm exception!", e);
                    throw e;
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error("close connection exception!", e);
                    throw e;
                }
            }
        }
    }
    
    public static void insertSubScript(String sql, List<SubscriptValue> subPushs) throws Exception {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstm = conn.prepareStatement(sql);
            String[] msgDArr = new String[5];
            for (SubscriptValue subPushValue : subPushs) {
                pstm.setString(1, subPushValue.getAcctId());
                pstm.setString(2, subPushValue.getClientSerialNum());
                pstm.setString(3, subPushValue.getSenderSubId());
                pstm.setString(4, subPushValue.getRouterId());
                pstm.setString(5, subPushValue.getClientId());
                if (null != subPushValue.getMessageData()) {
                    msgDArr = subPushValue.getMessageData().split(LoadSubPushUtil.sep);
                }
                pstm.setString(6, msgDArr[0]);
                pstm.setString(7, msgDArr[1]);
                pstm.setString(8, msgDArr[4]);
                pstm.addBatch();
            }
            pstm.executeBatch();
            conn.commit();
        } catch (Exception e) {
            log.error("insert mysql failed!", e);
            throw e;
        } finally {
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    log.error("close pstm exception!", e);
                    throw e;
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error("close connection exception!", e);
                    throw e;
                }
            }
        }
    }
    
    
    
}
