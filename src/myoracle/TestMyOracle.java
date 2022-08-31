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
            
            System.out.println("未索引："+(stop-start));
            
            start = System.nanoTime();
            pre.setDouble(1, 0.0000);
            pre.setDouble(2, 0.0000);
            pre.setString(3, "'"+"0"+"'");
            pre.setString(4, "'"+"125090"+"'");
            res = pre.executeUpdate();
            stop = System.nanoTime();
            System.out.println("有索引："+(stop-start));
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
                // 注意关闭的顺序，最后使用的最先关闭
                if (pre != null)
                    pre.close();
                if (con != null)
                    con.close();
                System.out.println("数据库连接已关闭！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(res);
    }
    
    /**
     * 一个非常标准的连接Oracle数据库的示例代码
     */
    public static Connection getConnection() {
        Connection con = null;// 创建一个数据库连接
        PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
        ResultSet result = null;// 创建一个结果集对象
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
            System.out.println("开始尝试连接数据库！");
            String url = "jdbc:oracle:thin:@192.168.1.166:1521:ctsdb";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
            String user = "cts_dev";// 用户名,系统默认的账户名
            String password = "cts_dev";// 你安装时选设置的密码
            con = DriverManager.getConnection(url, user, password);// 获取连接
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return con;
    }
}
