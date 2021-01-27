package service;

import java.util.ArrayList;
import java.util.jar.JarOutputStream;

import config.jdbc;
import dao.QuestionDao;
import entity.Questions;

/**
 * @author 玉竹
 */
public class QuestionService {

	//随机出题的集合
	private ArrayList<Questions> getList =  new ArrayList<Questions>();
	//展示题的集合
	private ArrayList<Questions> showList =  new ArrayList<Questions>();
	private QuestionDao dao = new QuestionDao();

	/**
	 * 随机选题
	 * @param amount 出题数
	 * @return  题目集合
	 */
	public ArrayList<Questions> getPaper(int amount,String table){
		//index数组存储0到题数减一的amount个随机数
		int [] index = jdbc.getRandomNumber(dao.getQuestionRow(table), amount);
		//越界则返回空
		if(amount > dao.getQuestionRow(table)) {
			return null;
		}
		System.out.println(amount + table);
		//获取题目
		for(int i = 0;i < amount ;i++) {
			getList.add(dao.getQuestions(index[i],table));
		}
		return getList;
	}

	/**
	 * 展示题目
	 * @param table
	 * @return 题目表
	 */
	public ArrayList<Questions> showQuestions(String table) {
		for(int i = 0;i < dao.getQuestionRow(table) ;i++) {
			showList.add(dao.getQuestions(i,table));
		}
		return showList;
	}

	/**
	 * 向题库里添加题目
	 * @param question  问题对象，含有问题的信息
	 * @return  添加结果
	 */
	public String addQuestion(Questions question) {
		if(dao.addQuestion(question) == 1) {
			return "添加成功";
		}else {
			return "添加失败";
		}
	}

	/**
	 * 修改答案
	 * @param question  修改答案的问题
	 * @param option   需要更改的选项，选项内容或答案选项，具体是哪个有content的取值决定
	 * @param content   修改选项内容的选项内容
	 * @return
	 */
	public String alterQuestion(Questions question,String option,String content) {
		if(dao.alterQuestion(question, option, content) == 1) {
			return "修改成功";
		}else {
			return "修改失败";
		}
	}
	
}

