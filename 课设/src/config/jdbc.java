package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.Random;


/**
 * @author 玉竹
 */
public class jdbc {
    /**
     * 数据库连接
     * @param DBName  数据库名
     * @param id  数据库管理员id
     * @param password  数据库管理员密码
     * @return 连接好的数据库
     */
    public static Connection connectDB(String DBName, String id, String password) {
        Connection con = null;

        String uri = "jdbc:mysql://127.0.0.1:3306/" + DBName + "?" + "useSSL = false & serverTimezone = UTC & characterEncoding = utf-8";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(uri, id, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 返回0 到 max - 1 的amount个不同随机数
     * @param max  总题数
     * @param amount 出题数
     * @return  随机数数组
     */
    public static int[] getRandomNumber(int max, int amount) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        //将LinkedList存入所有数字
        for (int i = 0; i < max; i++) {
            list.add(i);
        }
        //结果数组
        int [] result = new int[amount];
        while (amount > 0) {
            //随机获得LinkedList的索引
            int index = new Random().nextInt(list.size());
            //从list的索引处删除数字
            int m = list.remove(index);
            //存入结果数组
            result[amount - 1] = m;
            amount--;
        }
        return result;
    }
}