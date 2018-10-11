package excelexport.db;

import excelexport.db.oracle.OracleDbUtil;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Title: DbUtil
 * Description: 数据库工具类
 *
 * @author zhaomenghui
 * @version 1.0
 * @createDate 2018/9/20
 */
public class DbUtil {
    private final static String driver   = "com.mysql.jdbc.Driver";
    private final static String url      = "jdbc:mysql://localhost:3306/test";
    private final static String username = "root";
    private final static String password = "admin";

    private static Connection getConn(String dbName) {
        assert dbName != null && !dbName.equals("");
        if (dbName.equals("mysql")) {
            return getMysqlConn();
        } else if (dbName.equals("oracle")) {
            return OracleDbUtil.getConn();
        }
        return null;
    }

    private static Connection getMysqlConn() {
        Connection conn = null;
        try {

            // 注册 JDBC 驱动
            Class.forName(driver);

            // 打开链接
            System.out.println("连接数据库...");
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static int execute(String sql, String dbName) throws Exception {
        Statement  stmt = null;
        Connection conn = getConn(dbName);
        stmt = (Statement) conn.createStatement();
        int ret = stmt.executeUpdate(sql);
        // 完成后关闭
        stmt.close();
        conn.close();
        return ret;
    }

    public static <T> List<T> query(String sql, Class<T> clz, String dbName) {
        StringBuilder sb       = null;
        List<T>       list     = null;
        BeanInfo      beanInfo = null;

        try {
            list = new ArrayList<>();
            beanInfo = Introspector.getBeanInfo(clz, Object.class);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            Statement stmt = (Statement) getConn(dbName).createStatement();
            ResultSet rs   = stmt.executeQuery(sql);
            sb = new StringBuilder(10);
            T t = null;
            while (rs.next()) {  //通过next来索引：判断是否有下一个记录
                t = clz.getDeclaredConstructor().newInstance();
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    Method writeMethod = propertyDescriptor.getWriteMethod();
//                    Object o         = rs.getObject(propertyDescriptor.getName(),propertyDescriptor.getPropertyType());
                    Object o = rs.getObject(propertyDescriptor.getName());
                    writeMethod.invoke(t, o);
                }
                System.out.println();
                list.add(t);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
