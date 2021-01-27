package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.jdbc;
import entity.Questions;

/**
 * @author 玉竹
 */

public class QuestionDao {
	Connection con;
	ResultSet rs;
	/**
	 *获得随题目
	 * @param index 索引
	 * @return Questions对象
	 */
	public Questions getQuestions(int index ,String table){
		//题目
		String question = null;
		//A选项
		String A = null;
		//B选项
		String B = null;
		//C选项
		String C = null;
		//D选项
		String D = null;
		//答案
		String answer = null;
		String SQL = "select * from " + table;
			
		con = jdbc.connectDB("Text", "root", "20000420zjy");
		if(con == null) {
			return null;
		}
		try {
			//预处理
			PreparedStatement ps = con.prepareStatement(SQL);
			//查询
			rs = ps.executeQuery(SQL);
			//游标指到最后一行
			rs.last();
			//游标指向索引处，获取索引处的信息
			//数据库游标从0到row-1
			rs.absolute(index);
			if(rs.next()){
				//获取到成员属性
				question = rs.getString(1) + "\n";
				A = rs.getString(2) + "\n";
				B = rs.getString(3) + "\n";
				C = rs.getString(4) + "\n";
				D = rs.getString(5) + "\n";
				answer = rs.getString(6);
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
		return new Questions(question +  "  " + A +  "  " + B + "  " + C + "  " + D,answer);
	}
	/**
	 *增加题目
	 * @param completeQuestion 在文本框输入的题目
	 * @return 1 修改成功，0 修改失败
	 */
	public int addQuestion(Questions completeQuestion) {
		//修改成功与否的指标
		int OK = 0;
		//题目
		String question = completeQuestion.getQuestion();
		//A选项
		String A = completeQuestion.getA();
		//B选项
		String B = completeQuestion.getB();
		//C选项
		String C = completeQuestion.getC();
		//D选项
		String D = completeQuestion.getD();
		//答案
		String answer = completeQuestion.getAnswer();
		String SQL = "insert into questions values(?,?,?,?,?,?)";
		
		con = jdbc.connectDB("Text", "root", "20000420zjy");
		if(con == null) {
			return 0;
		}
		try {
			//预处理
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, question);
			ps.setString(2, A);
			ps.setString(3, B);
			ps.setString(4, C);
			ps.setString(5, D);
			ps.setString(6, answer);
			//添加题目
			OK = ps.executeUpdate();
		}catch(Exception e){
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
	 * 返回题目的选项
	 * @param question 文本框输入的题目
	 * @return 题目选项
	 */
	public Questions returnOptionContent(String question) {
		String A  = null;
		String B  = null;
		String C  = null;
		String D  = null;
		String SQL = "select * from questions where question = ?";

		con = jdbc.connectDB("Text", "root", "20000420zjy");
		if(con == null) {
			return null;
		}
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, question);
			rs = ps.executeQuery();
			while(rs.next()) {
				A = rs.getString(2);
				B = rs.getString(3);
				C = rs.getString(4);
				D = rs.getString(5);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			//关闭数据库
			con.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return new Questions(question,A,B,C,D,null);

	}

	/**
	 * 修改答案
	 * @param alterQuestion 要修改答案的题目
	 * @param alterOption  需要修改内容选项（答案选项不变）
	 * @param content  需要修改的选项内容（答案选项不变）
	 * @return 1 修改成功，0 修改失败
	 */
	public int alterQuestion(Questions alterQuestion,String alterOption, String content) {
		int OK = 0;
		String question = alterQuestion.getTitle();
		String answer = alterQuestion.getAnswer();
		//只修改答案选项
		String SQLOne = "update questions set answer = ? where question = ?";
		//只修改选项内容
		String SQLTwo = "update questions set " + alterOption + " = ? where question = ?";

		con = jdbc.connectDB("Text", "root", "20000420zjy");
		if(con == null) {
			return 0;
		}
		try {
			if(content == null) {
				//只修改答案选项
				//预处理
				PreparedStatement ps = con.prepareStatement(SQLOne);
				ps.setString(1, answer);
				ps.setString(2, question);
				//修改
				OK = ps.executeUpdate();
			}else{
				//只修改选项内容
				//预处理

				PreparedStatement ps = con.prepareStatement(SQLTwo);
				ps.setString(1, content);
				ps.setString(2, question);
				//修改
				OK = ps.executeUpdate();
			}
		}catch(Exception e){
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
	 *
	 * @param table 数据库名
	 * @return  数据库行数
	 */
	public int getQuestionRow(String table) {
		ResultSet rs;
		int row;
		con = jdbc.connectDB("Text", "root", "20000420zjy");
		String SQL = "SELECT count(*) from " + table;
		try {
			//预处理
			PreparedStatement ps = con.prepareStatement(SQL);
			//查询
			rs = ps.executeQuery();
			while(rs.next()) {
				//给成员属性赋值
				row = rs.getInt(1);
				return row;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			//关闭数据库
			con.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return 0;
	}
}