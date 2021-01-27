package service;

import dao.UserDao;
import entity.User;

/**
 * @author 玉竹
 */
public class UserService {

	private UserDao dao = new UserDao();

	/**
	 *   登陆
	 * @param account  账号
	 * @param password  密码
	 * @param type  登陆身份
	 * @return  登陆结果
	 */
	public String login(String account,String password,String type) {
		String truePassword = dao.selectPassword(account, type);
		//如果密码输入正确
		if(truePassword != null) {
			if(password.equals(truePassword)) {
				return "登陆成功";
			}
		}	
			return "用户名或密码错误";
	}

	/**
	 *  修改密码
	 * @param user  用户对象 从文本框获取，密码为用户输入密码
	 * @param passwordNew  修改的密码
	 * @param type  身份  老师或学生
	 * @return  修改结果
	 */
	public String changePassword(User user, String passwordNew,String type) {
		
		int judge = dao.changePassword(user,passwordNew, type);
		if(judge == 2) {
			return "原密码不正确,请重新输入";
		}else
			if(judge == 0){
				return "修改失败";
			}else
				if(judge == 1) {
					return "修改成功";
				}
		return null;
	}

	/**
	 * 注销
	 * @param user 账户信息
	 * @param type 账户类型
	 * @return 注销结果
	 */
	public String cancellation(User user, String type) {

		int judge = dao.cancellation(user,type);
		if(judge == 2) {
			return "原密码不正确,请重新输入";
		}else
		if(judge == 0){
			return "注销失败";
		}else
		if(judge == 1) {
			return "注销成功";
		}
		return null;
	}

	/**
	 *   修改数据库个性化
	 * @param signature
	 * @param sex
	 * @param location
	 * @param university
	 * @param type
	 * @param account
	 * @return  修改结果
	 */
	public String addIndividualization(String signature,String sex,String location,String university,String type,String account) {

		int judge = dao.addIndividualization(signature,sex,location,university,type,account);
		if(judge == 0){
			return "修改失败";
		}else
		if(judge == 1) {
			return "修改成功";
		}
		return null;
	}
}