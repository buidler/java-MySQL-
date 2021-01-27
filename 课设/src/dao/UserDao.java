package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.jdbc;
import entity.User;

/**
 * @author 玉竹
 */

public class UserDao {

	/**
	 * 登陆查询
	 * @param account 账户名
	 * @param type 学生或老师
	 * @return 密码
	 */
	public  String selectPassword(String account,String type) {
		Connection con = jdbc.connectDB("Text", "root", "20000420zjy");
		String SQL = "select * from " + type + " where account = ?";
		ResultSet rs;
		String password = null;
		
		try {
			//预处理
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, account);
			//查询
			rs = ps.executeQuery();
			while(rs.next()) {
				//查询结果给成员属性赋值
				password = rs.getString(2);
			}
		}catch(Exception e) {
			e.getStackTrace();
		}
		try {
			//关闭数据库
			con.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return password;
	}

	/**
	 * 注册插入
	 * @param user 用户对象
	 * @param type 教师或学生
	 * @return  1 修改成功，0 修改失败
	 */
	public  int addAccount(User user,String type) {
		// TODO Auto-generated method stub
		//成功与否指标
		int OK = 0;
		Connection con = jdbc.connectDB("Text", "root", "20000420zjy");
		String SQL = "insert into " + type + " (account,passwords,classNum) values(?,?,?)";
		
		try {
			//预处理
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, user.getAccount());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getClassNum());
			//查询
		    OK = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			//关闭数据库
			con.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return OK;
	}

	/**
	 * 个性化设置
	 * @param signature
	 * @param sex
	 * @param location
	 * @param university
	 * @param type
	 * @param account
	 * @return
	 */
	public  int addIndividualization(String signature,String sex,String location,String university,String type,String account) {
		// TODO Auto-generated method stub
		//成功与否指标
		int OK = 0;
		Connection con = jdbc.connectDB("Text", "root", "20000420zjy");
		String SQL = "update " + type + " set signature=?,sex =?,location=?,university=? WHERE account = ?";
		try {
			//预处理
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, signature);
			ps.setString(2, sex);
			ps.setString(3, location);
			ps.setString(4, university);
			ps.setString(5, account);
			//查询
			OK = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			//关闭数据库
			con.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return OK;
	}


	/**
	 *修改密码
	 * @param user 用户对象，存储文本框输入的密码（不一定正确）
	 * @param passwordNew  修改后的密码，文本框获取
	 * @param type  教师或学生
	 * @return 1 修改成功，0 修改失败，2 输入的原密码错误
	 */
	public int  changePassword(User user, String passwordNew,String type) {
		String passwordOld = user.getPassword();
        ResultSet rs;
        int OK = 0;

        Connection con = jdbc.connectDB("Text", "root", "20000420zjy");
        String SQLOne = "select * from " + type + " where account = ?";
        String SQLTwo = "update " + type + " set passwords = ? WHERE account = ?";
        try {
        	//预处理
        	PreparedStatement psOne = con.prepareStatement(SQLOne);
        	PreparedStatement psTwo = con.prepareStatement(SQLTwo);
        	psOne.setString(1, user.getAccount());
        	psTwo.setString(1, passwordNew);
			psTwo.setString(2, user.getAccount());
			//查询
        	rs = psOne.executeQuery();
        	if(rs.next()) {
        		//当原密码（用户输入）与数据库中不一致时
        		if(!rs.getString(2).equals(passwordOld)) {
            		return 2;
            	}else {
            		OK =  psTwo.executeUpdate();
            	}
        	}
        }catch (Exception e) {
        	e.printStackTrace();
         }
		try {
			//关闭数据库
			con.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return OK ;
		
	}

	/**
	 * 注销账号
	 * @param user  用户对象，存储文本框输入的密码（不一定正确
	 * @param type  教师或学生
	 * @return  1 修改成功，0 修改失败，2 输入的原密码错误
	 */
	public int cancellation(User user,String type){
		ResultSet rs;
		int OK = 0;

		Connection con = jdbc.connectDB("Text", "root", "20000420zjy");
		String SQLOne = "select * from " + type + " where account = ?";
		String SQLTwo = "delete from " + type + " where account = ?";
		try {
			//预处理
			PreparedStatement psOne = con.prepareStatement(SQLOne);
			PreparedStatement psTwo = con.prepareStatement(SQLTwo);
			psOne.setString(1, user.getAccount());
			psTwo.setString(1, user.getAccount());
			//查询
			rs = psOne.executeQuery();
			if(rs.next()) {
				//当原密码（用户输入）与数据库中不一致时
				if(!rs.getString(2).equals(user.getPassword())) {
					return 2;
				}else {
					OK =  psTwo.executeUpdate();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			//关闭数据库
			con.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return OK ;
	}

	/**
	 *   查找教师班级号
	 * @param account  账户信息
	 * @return  班级号
	 */
	public String searchClassNum(String account){
		Connection con = jdbc.connectDB("Text", "root", "20000420zjy");
		String SQL = "select * from teacher where account = ?";
		ResultSet rs;
		String classNum = null;
		try {
			//预处理
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, account);
			//查询
			rs = ps.executeQuery();
			while(rs.next()) {
				//查询结果给成员属性赋值
				classNum = rs.getString(3);
			}
		}catch(Exception e) {
			e.getStackTrace();
		}
		try {
			//关闭数据库
			con.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return classNum;

	}
}
