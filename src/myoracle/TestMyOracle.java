package myoracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMyOracle {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testPreToChar();
    }

    
    public static void testPreToChar(){
        String sql = "UPDATE stkNewPrice SET newPrice=?, closePrice=? WHERE exchId=? AND stkId=?";
        Connection con = getConnection();
        PreparedStatement pre = null;
        int res = 0;
        long start = 0;
        long stop = 0;
        try {
            pre = con.prepareStatement(sql);
            start = System.nanoTime();
            pre.setDouble(1, 0.0000);
            pre.setDouble(2, 0.0000);
            pre.setString(3, "0");
            pre.setString(4, "125090");
            res = pre.executeUpdate();
            stop = System.nanoTime();
            
            System.out.println("δ������"+(stop-start));
            
            start = System.nanoTime();
            pre.setDouble(1, 0.0000);
            pre.setDouble(2, 0.0000);
            pre.setString(3, "'"+"0"+"'");
            pre.setString(4, "'"+"125090"+"'");
            res = pre.executeUpdate();
            stop = System.nanoTime();
            System.out.println("��������"+(stop-start));
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                // ��һ������ļ�������رգ���Ϊ���رյĻ���Ӱ�����ܡ�����ռ����Դ
                // ע��رյ�˳�����ʹ�õ����ȹر�
                if (pre != null)
                    pre.close();
                if (con != null)
                    con.close();
                System.out.println("���ݿ������ѹرգ�");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(res);
    }
    
    /**
     * һ���ǳ���׼������Oracle���ݿ��ʾ������
     */
    public static Connection getConnection() {
        Connection con = null;// ����һ�����ݿ�����
        PreparedStatement pre = null;// ����Ԥ����������һ�㶼�������������Statement
        ResultSet result = null;// ����һ�����������
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");// ����Oracle��������
            System.out.println("��ʼ�����������ݿ⣡");
            String url = "jdbc:oracle:thin:@192.168.1.166:1521:ctsdb";// 127.0.0.1�Ǳ�����ַ��XE�Ǿ����Oracle��Ĭ�����ݿ���
            String user = "cts_dev";// �û���,ϵͳĬ�ϵ��˻���
            String password = "cts_dev";// �㰲װʱѡ���õ�����
            con = DriverManager.getConnection(url, user, password);// ��ȡ����
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return con;
    }
}
